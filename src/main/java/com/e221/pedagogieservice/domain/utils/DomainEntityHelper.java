package com.e221.pedagogieservice.domain.utils;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DomainEntityHelper {
    public static <T> T findOrCreate(
            JpaRepository<T,Long> repository,
            Object dto,
            Class<T> tClass,
            Function<T, Boolean> matchCriteria
            ) {
        T entity = MapperService.mapToEntity(dto,tClass);
        try {
            Field idField = dto.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Long id = (Long) idField.get(dto);

            if(id != null){
                return repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
            }
        }catch (Exception e) {
            throw new RuntimeException("Error checking ID field", e);
        }

        List<T> existingEntites = repository.findAll();
        Optional<T> matchingEntity = existingEntites.stream()
                .filter(matchCriteria::apply)
                .findFirst();

        return matchingEntity.orElseGet(()->repository.save(entity));
    }

    public static <T, D> T findOrUpdate(
            JpaRepository<T, Long> repository,
            D dto,
            Class<T> entityClass,
            Function<T, Boolean> matchCriteria,
            BiConsumer<T, D> fieldUpdater
    ) {
        Objects.requireNonNull(repository, "Repository cannot be null");
        Objects.requireNonNull(dto, "DTO cannot be null");
        Objects.requireNonNull(entityClass, "Entity class cannot be null");
        Objects.requireNonNull(matchCriteria, "Match criteria cannot be null");

        try {
            // Vérifie s’il existe un champ "id"
            Field idField = dto.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(dto);

            if (idValue instanceof Long id && id > 0) {
                return repository.findById(id)
                        .map(existing -> {
                            if (fieldUpdater != null) fieldUpdater.accept(existing, dto);
                            return repository.save(existing);
                        })
                        .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("DTO must have a valid Long id field", e);
        }

        // Recherche par critère métier (libelle, etc.)
        Optional<T> optional = repository.findAll()
                .stream()
                .filter(matchCriteria::apply)
                .findFirst();

        if (optional.isPresent()) {
            T entity = optional.get();
            if (fieldUpdater != null) fieldUpdater.accept(entity, dto);
            return repository.save(entity);
        }


        // ❌ 3️⃣ Aucun trouvé → on lève une erreur explicite
        throw new EntityNotFoundException(
                String.format("No matching %s found by id or criteria", entityClass.getSimpleName())
        );
    }

}
