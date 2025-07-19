package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CycleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.models.Cycle;
import com.e221.pedagogieservice.domain.repositories.CycleRepository;
import com.e221.pedagogieservice.domain.services.CycleService;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@Transactional
@Slf4j
public class CycleServiceImp extends DefaultServiceImp<Cycle, CycleDtoRequest, CycleDtoResponse> implements CycleService {

    private final CycleRepository repository;

    public CycleServiceImp(
            CycleRepository repository,
            DefaultSpecification<Cycle> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager
    ) {
        super(repository, defaultSpecification,cacheNameProvider, entityManager);
        this.repository = repository;
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "cycleList", allEntries = true),
            @CacheEvict(value = "cycle", key = "#id")
    })
    public CycleDtoResponse archive(Long id) {
        var cycle = getEntityById(id);
        cycle.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(cycle), CycleDtoResponse.class);
    }
}
