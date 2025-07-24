package com.e221.pedagogieservice.runtime.services.base;

import com.cheikh.commun.core.GenericEntity;
import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.exceptions.BadRequestException;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.exceptions.InternalServerErrorException;
import com.cheikh.commun.services.MapperService;
import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.constants.ErrorsMessages;
import com.e221.pedagogieservice.domain.models.Statut;
import com.e221.pedagogieservice.domain.utils.LoggingUtil;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Generic base service with:
 *  - soft delete support (archive flag)
 *  - dynamic uniqueness guard (libelle/code if present)
 *  - cache management (list + single)
 *  - override hooks for relationship-aware reload & mapping
 */
@Service
@Transactional
@Slf4j
public abstract class DefaultServiceImp<T extends GenericEntity<T>, D, R>
        implements DefaultService<T, D, R> {

    private final GenericRepository<T> repository;
    protected final DefaultSpecification<T> specification;
    private final CacheNameProvider cacheNameProvider;
    private final CacheManager cacheManager;
    @Autowired
    private ObjectMapper objectMapper;
    protected final Class<T> tClass;
    protected final Class<R> rClass;

    @SuppressWarnings("unchecked")
    public DefaultServiceImp(GenericRepository<T> repository,
                             DefaultSpecification<T> specification,
                             CacheNameProvider cacheNameProvider,
                             CacheManager cacheManager) {
        this.repository = repository;
        this.specification = specification;
        this.cacheNameProvider = cacheNameProvider;
        this.cacheManager = cacheManager;

        var type = (ParameterizedType) getClass().getGenericSuperclass();
        this.tClass = (Class<T>) type.getActualTypeArguments()[0];
        this.rClass = (Class<R>) type.getActualTypeArguments()[2];
    }

    protected GenericRepository<T> getRepository() {
        return this.repository;
    }

    /* =========================================================
     * CREATE
     * ========================================================= */
    @Override
    public R create(D d) {
        try {
            LoggingUtil.logInfo(this.getClass(), "create",
                    "beginning add new < %s >".formatted(tClass.getSimpleName()));

            // ---- Vérification unicité dynamique (libelle / code si présents) ----
            checkUniqueFields(d);

            // ---- Mapping DTO -> Entité ----
            T entity = MapperService.mapToEntity(d, tClass);

            // ---- Gestion des relations ----
            entity = createRelationships(entity, d);

            // ---- Sauvegarde ----
            T savedEntity = repository.save(entity);

            // ---- Reload complet + mapping ----
            T full = reloadWithRelationships(savedEntity.getId());
            R dto = mapToDto(full);

            refreshCaches(savedEntity.getId(), dto);
            log.info("Created {}", dto);
            return dto;

        } catch (Exception ex) {
            // Centralisation de la gestion des erreurs
            handleException(ex);
            return null; // Jamais atteint car handleException lance toujours une exception
        }
    }

    /**
     * Vérifie l'unicité des champs "libelle" et "code" si présents.
     */
    private void checkUniqueFields(D d) throws IllegalAccessException {

        // ---- Forcer statut = Actif si le DTO a un champ 'statut' ----
        try {
            Field statutField = d.getClass().getDeclaredField("statut");
            statutField.setAccessible(true);
            Object statutValue = statutField.get(d);
            if (statutValue == null) {
                statutField.set(d, Statut.Actif);
            }
        } catch (NoSuchFieldException ignored) {
            // pas de champ 'statut', on ignore
        }
        try {
            Field archiveField = d.getClass().getDeclaredField("archive");
            archiveField.setAccessible(true);
            Object archiveValue = archiveField.get(d);

            // Si null et type compatible, on force à false
            if (archiveValue == null) {
                if (archiveField.getType() == Boolean.class || archiveField.getType() == boolean.class) {
                    archiveField.set(d, false);
                }
            }
        } catch (NoSuchFieldException ignored) {
            // Pas de champ 'archive', on ignore
        }

        Map<String, Object> uniqueFields = new HashMap<>();

        try {
            Field libelleField = d.getClass().getDeclaredField("libelle");
            libelleField.setAccessible(true);
            Object value = libelleField.get(d);
            if (value != null) uniqueFields.put("libelle", value);
        } catch (NoSuchFieldException ignored) {}

        try {
            Field codeField = d.getClass().getDeclaredField("code");
            codeField.setAccessible(true);
            Object value = codeField.get(d);
            if (value != null) uniqueFields.put("code", value);
        } catch (NoSuchFieldException ignored) {}

        if (!uniqueFields.isEmpty()) {
            boolean exists = repository.exists((root, query, cb) -> {
                List<Predicate> preds = new ArrayList<>();
                uniqueFields.forEach((name, val) -> preds.add(cb.equal(root.get(name), val)));
                return cb.or(preds.toArray(new Predicate[0]));
            });
            if (exists) {
                throw new BadRequestException(
                        "%s avec le meme attribut existe déja"
                                .formatted(tClass.getSimpleName()));
            }
        }
    }

    /**
     * Gère toutes les exceptions pour centraliser les réponses appropriées.
     */
    private void handleException(Exception ex) {
        if (ex instanceof BadRequestException || ex instanceof EntityNotFoundException) {
            throw (RuntimeException) ex; // Laisse passer les exceptions métier
        }

        if (ex instanceof DataIntegrityViolationException) {
            throw new BadRequestException("%s %s"
                    .formatted(tClass.getSimpleName(), ErrorsMessages.ENTITY_EXISTS));
        }

        log.error(ex.getMessage(), ex);
        throw new InternalServerErrorException("%s %s"
                .formatted(ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
    }



    /* =========================================================
     * UPDATE (returns fully hydrated DTO)
     * ========================================================= */
    @Override
    public R update(long id, D d) {
        // 1. Update & save base entity
        T updatedEntity = updateEntity(id, d);

        // 2. Reload with relationships (subclasses override)
        T fullEntity = reloadWithRelationships(updatedEntity.getId());

        // 3. Map with subclass-aware mapping
        R response = mapToDto(fullEntity);

        // 4. Refresh caches using hydrated DTO
        refreshCaches(id, response);

        log.info("✅ Updated entity with full mapping: {}", response);
        return response;
    }

    /**
     * Performs the raw update/save without reloading relationships.
     * Subclasses rarely need to override.
     */
    protected T updateEntity(long id, D d) {
        try {
            T entity = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            ErrorsMessages.ENTITY_NOT_FOUND_MESSAGE));

            LoggingUtil.logInfo(
                    this.getClass(),
                    "update",
                    "begin update < %s > with id: %s"
                            .formatted(tClass.getSimpleName(), id));

            // Forcer les valeurs par défaut
            setDefaultValue(d, "statut", Statut.Actif);
            setDefaultValue(d, "archive", false);

            // Mise à jour de l'entité
            MapperService.patchEntityFromDto(entity, d);
            entity = updateRelationships(entity, d);
            T saved = repository.save(entity);

            LoggingUtil.logInfo(
                    this.getClass(),
                    "update",
                    "< %s > updated successfully".formatted(tClass.getSimpleName()));

            return saved;

        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("%s %s"
                    .formatted(tClass.getName(), ErrorsMessages.ENTITY_EXISTS));
        } catch (Exception ex) {
            throw new InternalServerErrorException("%s %s"
                    .formatted(ErrorsMessages.ADD_ENTITY_ERROR, tClass.getName()));
        }
    }

    private void setDefaultValue(D dto, String fieldName, Object defaultValue) {
        try {
            Field field = dto.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value == null) {
                field.set(dto, defaultValue);
            }
        } catch (NoSuchFieldException ignored) {
            // Pas de champ, on ignore
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to set default value for field: " + fieldName, e);
        }
    }


    /* =========================================================
     * FIND ALL (cached, non-archived default)
     * ========================================================= */
    @Override
    public PageResponse<R> findAll(int page, int size) {
        String listCache = cacheNameProvider.getListCacheName(rClass);
        Cache cache = cacheManager.getCache(listCache);

        @SuppressWarnings("unchecked")
        List<R> cachedList = (cache != null)
                ? (List<R>) cache.get("ALL", List.class)
                : null;

        if (cachedList == null) {
            log.info("📦 Cache MISS — loading active items from DB for {}",
                    rClass.getSimpleName());

            List<T> activeEntities = repository.findAll(specification.isArchiveFalse());
            cachedList = mapListToDto(activeEntities);

            if (cache != null) {
                cache.put("ALL", cachedList);
                log.info("✅ Stored {} active items in cache {}",
                        cachedList.size(), listCache);
            }
        } else {
            log.info("✅ Cache HIT for {}", listCache);
        }

        return toPageResponse(cachedList, page, size);
    }

    /* =========================================================
     * SOFT DELETE (archive flag)
     * ========================================================= */
    @Override
    public R delete(Long id) {
        T entity = getEntityById(id);
        try {
            Field archiveField = entity.getClass().getDeclaredField("archive");
            archiveField.setAccessible(true);
            archiveField.set(entity, true);
            T archived = repository.save(entity);

            // reload full & map
            T full = reloadWithRelationships(archived.getId());
            R dto = mapToDto(full);

            evictFromCaches(id);
            refreshCaches(id, dto);

            log.info("🗑️ Archived entity (soft delete) and updated cache for id {}", id);
            return dto;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Entity does not support archiving", e);
        }
    }

    /* =========================================================
     * RESTORE (un-archive)
     * ========================================================= */
    @Override
    public R restore(Long id) {
        T entity = getEntityById(id);
        try {
            Field archiveField = entity.getClass().getDeclaredField("archive");
            archiveField.setAccessible(true);
            archiveField.set(entity, false);
            T restored = repository.save(entity);

            // reload full & map
            T full = reloadWithRelationships(restored.getId());
            R dto = mapToDto(full);

            evictFromCaches(id);
            refreshCaches(id, dto);

            log.info("♻️ Restored entity and updated cache for id {}", id);
            return dto;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Entity does not support restoring (no archive field)", e);
        }
    }

    /* =========================================================
     * SINGLE
     * ========================================================= */
    @Override
    public R getById(Long id) {
        // Default: just load and map simple
        // Subclasses can override if they want caching or relationship fetch
        T entity = reloadWithRelationships(id);
        return mapToDto(entity);
    }

    public R getByIdWithoutCache(Long id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ErrorsMessages.ENTITY_NOT_FOUND_MESSAGE));
        return mapToDto(entity);
    }

    @Override
    public T getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        ErrorsMessages.ENTITY_NOT_FOUND_MESSAGE));
    }

    /* =========================================================
     * CACHE SUPPORT
     * ========================================================= */
    protected void refreshCaches(Long id, R dto) {
        String listCache = cacheNameProvider.getListCacheName(rClass);
        String singleCache = cacheNameProvider.getSingleCacheName(rClass);

        Cache list = cacheManager.getCache(listCache);
        Cache single = cacheManager.getCache(singleCache);

        if (single != null) single.evict(id);
        if (list != null) list.clear();

        // reload list (non-archived)
        List<T> activeEntities = repository.findAll(specification.isArchiveFalse());
        List<R> activeDtos = mapListToDto(activeEntities);

        if (list != null) {
            list.put("ALL", activeDtos);
            log.info("♻️ Reloaded cache {} with {} active items", listCache, activeDtos.size());
        }
        if (single != null && dto != null) {
            single.put(id, dto);
            log.info("♻️ Reloaded single cache {} with id {}", singleCache, id);
        }
    }

    protected void evictFromCaches(Long id) {
        String listCache = cacheNameProvider.getListCacheName(rClass);
        String singleCache = cacheNameProvider.getSingleCacheName(rClass);

        if (cacheManager.getCache(singleCache) != null) {
            cacheManager.getCache(singleCache).evict(id);
        }
        if (cacheManager.getCache(listCache) != null) {
            cacheManager.getCache(listCache).clear();
        }
        log.info("🧹 Cleared caches: {}, {} for id {}", singleCache, listCache, id);
    }

    /* =========================================================
     * HOOKS FOR SUBCLASSES
     * ========================================================= */

    /**
     * Reload entity with required relationships.
     * Override in subclasses that need JOIN FETCH behavior.
     */
    protected T reloadWithRelationships(Long id) {
        // default: simple find
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found id=" + id));
    }

    /**
     * Map entity → DTO. Override when relationships require custom mapping.
     */
    protected R mapToDto(T entity) {
        return MapperService.mapToDtoResponse(entity, rClass);
    }

    /**
     * Map list → list of DTOs. Subclasses may override to use custom mapping.
     */
    protected List<R> mapListToDto(List<T> entities) {
        return MapperService.mapToListDto(entities, rClass);
    }

    /* =========================================================
     * RELATIONSHIP HOOKS (override in Subservices)
     * ========================================================= */
    protected T createRelationships(T entity, D dto) {
        return entity;
    }

    protected T updateRelationships(T entity, D dto) {
        return entity;
    }

    /* =========================================================
     * Paging helper
     * ========================================================= */
    protected PageResponse<R> toPageResponse(List<R> fullList, int page, int size) {
        int totalElements = fullList.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        int fromIndex = Math.min(page * size, totalElements);
        int toIndex = Math.min(fromIndex + size, totalElements);
        List<R> pageContent = fullList.subList(fromIndex, toIndex);

        return PageResponse.<R>builder()
                .content(pageContent)
                .number(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .first(page == 0)
                .last(page >= totalPages - 1)
                .build();
    }

    @Override
    @Transactional
    public R patchFields(Long id, Map<String, Object> fields) {
        // 1. Fetch entity
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        tClass.getSimpleName() + " not found id=" + id));

        // 2. Apply partial updates via reflection
        fields.forEach((fieldName, value) -> {
            try {
                Field field = tClass.getDeclaredField(fieldName);
                field.setAccessible(true);

                // Convert value automatically using ObjectMapper
                Object convertedValue = objectMapper.convertValue(value, field.getType());
                field.set(entity, convertedValue);

            } catch (NoSuchFieldException e) {
                log.warn("Field '{}' not found in {}. Ignored.", fieldName, tClass.getSimpleName());
            } catch (IllegalAccessException e) {
                log.error("Could not access field '{}' in {}.", fieldName, tClass.getSimpleName(), e);
            }
        });

        // 3. Save the patched entity
        T saved = repository.save(entity);

        // 4. Reload relationships (if needed) and map to response
        T reloaded = reloadWithRelationships(saved.getId());
        R dto = mapToDto(reloaded);

        // 5. Refresh cache
        refreshCaches(saved.getId(), dto);

        return dto;
    }


}
