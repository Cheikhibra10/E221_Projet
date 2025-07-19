package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.HoraireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.HoraireDtoResponse;
import com.e221.pedagogieservice.domain.models.Horaire;
import com.e221.pedagogieservice.domain.repositories.HoraireRepository;
import com.e221.pedagogieservice.domain.services.HoraireService;
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
public class HoraireServiceImp
        extends DefaultServiceImp<Horaire, HoraireDtoRequest, HoraireDtoResponse>
        implements HoraireService {

    private final HoraireRepository horaireRepository;

    public HoraireServiceImp(
            HoraireRepository horaireRepository,
            DefaultSpecification<Horaire> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager entityManager
    ) {
        super(horaireRepository, defaultSpecification,cacheNameProvider,entityManager);
        this.horaireRepository = horaireRepository;
    }

    @Override
    public HoraireDtoResponse archive(Long id) {
        return null;
    }


//    // ✅ Archive avec éviction
//    @Override
//    @Caching(evict = {
//            @CacheEvict(value = "horaireList", allEntries = true),
//            @CacheEvict(value = "horaire", key = "#id")
//    })
//    public HoraireDtoResponse archive(Long id) {
//        var entity = getEntityById(id);
//        entity.setArchive(true);
//        return MapperService.mapToDtoResponse(horaireRepository.save(entity), HoraireDtoResponse.class);
//    }
}
