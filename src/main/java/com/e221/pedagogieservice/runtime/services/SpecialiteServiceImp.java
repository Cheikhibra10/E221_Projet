package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CalendrierDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.mapper.SpecialiteMapper;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.SpecialiteRepository;
import com.e221.pedagogieservice.domain.services.SpecialiteService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class    SpecialiteServiceImp
        extends DefaultServiceImp<Specialite, SpecialiteDtoRequest, SpecialiteDtoResponse>
        implements SpecialiteService {

    private final SpecialiteRepository repository;
    private final DefaultSpecification<Specialite> specification;
    private final MentionRepository mentionRepository;
    private final DomaineRepository domaineRepository;
    private final NiveauRepository niveauRepository;
    private final EntityManager entityManager;
    private final CacheNameProvider cacheNameProvider;
    private final CacheManager cacheManager;
    private final SpecialiteMapper specialiteMapper;

    // If you later wire MapStruct mappers, add them here
    // private final SpecialiteMapper specialiteMapper;
    // private final NiveauMapper niveauMapper;

    public SpecialiteServiceImp(
            SpecialiteRepository repository,
            DefaultSpecification<Specialite> specification,
            MentionRepository mentionRepository,
            DomaineRepository domaineRepository,
            NiveauRepository niveauRepository,
            EntityManager entityManager,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, SpecialiteMapper specialiteMapper
            // SpecialiteMapper specialiteMapper,
            // NiveauMapper niveauMapper
    ) {
        super(repository, specification, cacheNameProvider, cacheManager);
        this.repository = repository;
        this.specification = specification;
        this.mentionRepository = mentionRepository;
        this.domaineRepository = domaineRepository;
        this.niveauRepository = niveauRepository;
        this.entityManager = entityManager;
        this.cacheNameProvider = cacheNameProvider;
        this.cacheManager = cacheManager;
        // this.specialiteMapper = specialiteMapper;
        // this.niveauMapper = niveauMapper;
        this.specialiteMapper = specialiteMapper;
    }

    /* ------------------------------------------------------------------
     *  ARCHIVE (soft delete) + cache eviction
     * ------------------------------------------------------------------ */
    @Override
    @Caching(evict = {
            @CacheEvict(value = "specialiteList", allEntries = true),
            @CacheEvict(value = "specialite", key = "#id")
    })
    public SpecialiteDtoResponse archive(Long id) {
        Specialite entity = getEntityById(id);
        entity.setArchive(true);
        Specialite saved = repository.save(entity);
        // refresh list/single caches manually if desired
        refreshCaches(saved.getId(), MapperService.mapToDtoResponse(saved, SpecialiteDtoResponse.class));
        return MapperService.mapToDtoResponse(saved, SpecialiteDtoResponse.class);
    }

    @Override
    protected Specialite reloadWithRelationships(Long id) {
        return repository.findByIdWithNiveaux(id)
                .orElseThrow(() -> new EntityNotFoundException("Specialite not found id=" + id));
    }

    @Override
    protected List<SpecialiteDtoResponse> mapListToDto(List<Specialite> entities) {
        return entities.stream()
                .map(this::mapSpecialiteToDtoWithNiveaux)
                .toList();
    }

    @Override
    protected SpecialiteDtoResponse mapToDto(Specialite specialite) {
        return mapSpecialiteToDtoWithNiveaux(specialite);
    }

    /* ------------------------------------------------------------------
     *  Relationship wiring on CREATE
     * ------------------------------------------------------------------ */
    @Override
    protected Specialite createRelationships(Specialite specialite, SpecialiteDtoRequest dto) {
        // Mention
        if (dto.getMentionId() != null) {
            Mention mention = DomainEntityHelper.findStrictById(mentionRepository, dto.getMentionId(), Mention.class);
            specialite.setMention(mention);
        } else {
            specialite.setMention(null);
        }

        // Domaine
        if (dto.getDomaineId() != null) {
            Domaine domaine = DomainEntityHelper.findStrictById(domaineRepository, dto.getDomaineId(), Domaine.class);
            specialite.setDomaine(domaine);
        } else {
            specialite.setDomaine(null);
        }

        // Niveaux via join entity
        if (dto.getNiveaux() != null && !dto.getNiveaux().isEmpty()) {
            List<NiveauSpecialite> links = dto.getNiveaux().stream()
                    .map(niveauId -> {
                        Niveau niveau = niveauRepository.findById(niveauId)
                                .orElseThrow(() -> new EntityNotFoundException("Niveau not found id=" +niveauId));
                        NiveauSpecialite ns = new NiveauSpecialite();
                        ns.setSpecialite(specialite);
                        ns.setNiveau(niveau);
                        return ns;
                    })
                    .toList();
            specialite.getNiveauxSpecialites().addAll(links);
        }

        return specialite;
    }

    /* ------------------------------------------------------------------
     *  Relationship wiring on UPDATE
     * ------------------------------------------------------------------ */
    @Override
    protected Specialite updateRelationships(Specialite specialite, SpecialiteDtoRequest dto) {
        // Mention
        if (dto.getMentionId() != null) {
            Mention mention = DomainEntityHelper.findStrictById(mentionRepository, dto.getMentionId(), Mention.class);
            specialite.setMention(mention);
        } else {
            specialite.setMention(null);
        }

        // Domaine
        if (dto.getDomaineId() != null) {
            Domaine domaine = DomainEntityHelper.findStrictById(domaineRepository, dto.getDomaineId(), Domaine.class);
            specialite.setDomaine(domaine);
        } else {
            specialite.setDomaine(null);
        }


        // Niveaux join rebuild
        if (dto.getNiveaux() != null) {          // only if caller included the field
            specialite.getNiveauxSpecialites().clear();
            if (!dto.getNiveaux().isEmpty()) {
                List<NiveauSpecialite> links = dto.getNiveaux().stream()
                        .map(niveauId -> {
                            Niveau niveau = niveauRepository.findById(niveauId)
                                    .orElseThrow(() -> new EntityNotFoundException("Niveau not found id=" + niveauId));
                            NiveauSpecialite ns = new NiveauSpecialite();
                            ns.setSpecialite(specialite);
                            ns.setNiveau(niveau);
                            return ns;
                        })
                        .toList();
                specialite.getNiveauxSpecialites().addAll(links);
            }
        }
        return specialite;
    }

    /* ------------------------------------------------------------------
     *  Helper: map entity → DTO incl. niveaux
     * ------------------------------------------------------------------ */
    private SpecialiteDtoResponse mapSpecialiteToDtoWithNiveaux(Specialite s) {
        SpecialiteDtoResponse dto = MapperService.mapToDtoResponse(s, SpecialiteDtoResponse.class);

        List<NiveauSpecialite> links = s.getNiveauxSpecialites();
        if (links == null || links.isEmpty()) {
            dto.setNiveaux(Collections.emptyList());
            return dto;
        }

        List<NiveauDtoResponse> niveaux = links.stream()
                .map(NiveauSpecialite::getNiveau)
                .filter(Objects::nonNull)
                .map(niv -> MapperService.mapToDtoResponse(niv, NiveauDtoResponse.class))
                .toList();

        dto.setNiveaux(niveaux);
        return dto;
    }

}



