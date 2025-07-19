package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.repositories.MentionRepository;
import com.e221.pedagogieservice.domain.services.MentionService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class MentionServiceImp
        extends DefaultServiceImp<Mention, MentionDtoRequest, MentionDtoResponse>
        implements MentionService {

    private final MentionRepository repository;
    private final DomaineRepository domaineRepository;
    private final EntityManager entityManager;

    public MentionServiceImp(
            MentionRepository repository,
            DefaultSpecification<Mention> defaultSpecification,
            DomaineRepository domaineRepository,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, EntityManager entityManager
    ) {
        super(repository, defaultSpecification, cacheNameProvider, cacheManager);
        this.repository = repository;
        this.domaineRepository = domaineRepository;
        this.entityManager = entityManager;
    }




    // ✅ Archive avec éviction
    @Override
    @Caching(evict = {
            @CacheEvict(value = "mentionList", allEntries = true),
            @CacheEvict(value = "mention", key = "#id")
    })
    public MentionDtoResponse archive(Long id) {
        var mention = getEntityById(id);
        mention.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(mention), MentionDtoResponse.class);
    }

    // 🔗 Gestion relation lors de la création
    @Override
    protected Mention createRelationships(Mention mention, MentionDtoRequest dto) {
        if (dto.getDomaine() != null) {
            Domaine domaine = DomainEntityHelper.findOrCreateStrict(
                    domaineRepository,
                    dto.getDomaine(),
                    Domaine.class,
                    root -> root.get("libelle").in(dto.getDomaine().getLibelle()),
                    MapperService::patchEntityFromDto,
                    entityManager
            );
            mention.setDomaine(domaine);
        }
        return mention;
    }


    // 🔗 Gestion relation lors de la mise à jour
    @Override
    protected Mention updateRelationships(Mention mention, MentionDtoRequest dto) {
        if (dto.getDomaine() != null) {
            Domaine domaine = DomainEntityHelper.findOrUpdate(
                    domaineRepository,
                    dto.getDomaine(),
                    Domaine.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getDomaine().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            mention.setDomaine(domaine);
        } else {
            mention.setDomaine(null);
        }
        return mention;
    }
}
