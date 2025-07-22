package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.CycleRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.services.NiveauService;
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
public class NiveauServiceImp
        extends DefaultServiceImp<Niveau, NiveauDtoRequest, NiveauDtoResponse>
        implements NiveauService {

    private final NiveauRepository repository;
    private final CycleRepository cycleRepository;
    private final EntityManager entityManager;

    public NiveauServiceImp(
            NiveauRepository repository,
            DefaultSpecification<Niveau> defaultSpecification, CycleRepository cycleRepository,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, EntityManager entityManager
    ) {
        super(repository, defaultSpecification, cacheNameProvider, cacheManager);
        this.repository = repository;
        this.cycleRepository = cycleRepository;
        this.entityManager = entityManager;
    }

    @Override
    protected Niveau createRelationships(Niveau niveau, NiveauDtoRequest dto) {
        if (dto.getCycleId() != null) {
            Cycle cycle = DomainEntityHelper.findStrictById(cycleRepository, dto.getCycleId(), Cycle.class);
            niveau.setCycle(cycle);
        } else {
            niveau.setCycle(null);
        }
        return niveau;
    }

    @Override
    protected Niveau updateRelationships(Niveau niveau, NiveauDtoRequest dto) {
        if (dto.getCycleId() != null) {
            if (dto.getCycleId() > 0) {
                Cycle cycle = DomainEntityHelper.findStrictById(cycleRepository, dto.getCycleId(), Cycle.class);
                niveau.setCycle(cycle);
            } else {
                niveau.setCycle(null);
            }
        }
        return niveau;
    }


    // ✅ Archive avec éviction
    @Override
    @Caching(evict = {
            @CacheEvict(value = "niveauList", allEntries = true),
            @CacheEvict(value = "niveau", key = "#id")
    })
    public NiveauDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), NiveauDtoResponse.class);
    }
}

