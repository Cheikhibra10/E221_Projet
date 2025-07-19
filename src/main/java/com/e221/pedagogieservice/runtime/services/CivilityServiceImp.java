package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CivilityDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CivilityDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.models.Civility;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.repositories.CivilityRepository;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.services.CivilityService;
import com.e221.pedagogieservice.domain.services.DomaineService;
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
public class CivilityServiceImp extends DefaultServiceImp<Civility, CivilityDtoRequest, CivilityDtoResponse> implements CivilityService {

    private final CivilityRepository civilityRepository;

    public CivilityServiceImp(
            CivilityRepository civilityRepository,
            DefaultSpecification<Civility> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager
    ) {
        super(civilityRepository, defaultSpecification, cacheNameProvider, entityManager);
        this.civilityRepository = civilityRepository;
    }


    // ✅ Archive avec éviction du cache liste + cache id
    @Override
    @Caching(evict = {
            @CacheEvict(value = "civilityList", allEntries = true),
            @CacheEvict(value = "civility", key = "#id")
    })
    public CivilityDtoResponse archive(Long id) {
        var civility = getEntityById(id);
        civility.setArchive(true);
        civilityRepository.save(civility);
        return MapperService.mapToDtoResponse(civility, CivilityDtoResponse.class);
    }
}
