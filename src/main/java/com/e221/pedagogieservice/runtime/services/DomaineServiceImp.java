package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.services.DomaineService;
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
public class DomaineServiceImp extends DefaultServiceImp<Domaine, DomaineDtoRequest, DomaineDtoResponse> implements DomaineService {

    private final DomaineRepository domaineRepository;

    public DomaineServiceImp(
            DomaineRepository domaineRepository,
            DefaultSpecification<Domaine> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager
    ) {
        super(domaineRepository, defaultSpecification, cacheNameProvider, entityManager);
        this.domaineRepository = domaineRepository;
    }


    // ✅ Archive avec éviction du cache liste + cache id
    @Override
    @Caching(evict = {
            @CacheEvict(value = "domaineList", allEntries = true),
            @CacheEvict(value = "domaine", key = "#id")
    })
    public DomaineDtoResponse archive(Long id) {
        var domaine = getEntityById(id);
        domaine.setArchive(true);
        domaineRepository.save(domaine);
        return MapperService.mapToDtoResponse(domaine, DomaineDtoResponse.class);
    }
}
