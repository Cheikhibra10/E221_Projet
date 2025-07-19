package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.SalleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SalleDtoResponse;
import com.e221.pedagogieservice.domain.models.Salle;
import com.e221.pedagogieservice.domain.repositories.SalleRepository;
import com.e221.pedagogieservice.domain.services.SalleService;
import com.e221.pedagogieservice.runtime.config.CacheNameProvider;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class SalleServiceImp extends DefaultServiceImp<Salle, SalleDtoRequest, SalleDtoResponse> implements SalleService {

    private final SalleRepository salleRepository;

    public SalleServiceImp(
            SalleRepository salleRepository,
            DefaultSpecification<Salle> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager
    ) {
        super(salleRepository, defaultSpecification, cacheNameProvider, entityManager);
        this.salleRepository = salleRepository;
    }


    // ✅ Archive avec éviction du cache liste + cache id
    @Override
    @Caching(evict = {
            @CacheEvict(value = "salleList", allEntries = true),
            @CacheEvict(value = "salle", key = "#id")
    })
    public SalleDtoResponse archive(Long id) {
        var salle = getEntityById(id);
        salle.setArchive(true);
        salleRepository.save(salle);
        return MapperService.mapToDtoResponse(salle, SalleDtoResponse.class);
    }
}
