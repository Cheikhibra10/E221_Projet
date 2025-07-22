package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.EvenementRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.SemestreRepository;
import com.e221.pedagogieservice.domain.services.EvenementService;
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
public class EvenementServiceImp
        extends DefaultServiceImp<Evenement, EvenementDtoRequest, EvenementDtoResponse>
        implements EvenementService {

    private final EvenementRepository evenementRepository;
    private final SemestreRepository semestreRepository;
    private final NiveauRepository niveauRepository;
    private final EntityManager entityManager;
    private final CacheManager cacheManager;

    public EvenementServiceImp(
            EvenementRepository evenementRepository,
            DefaultSpecification<Evenement> defaultSpecification,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager,
            EntityManager entityManager,
            SemestreRepository semestreRepository,
            NiveauRepository niveauRepository
    ) {
        super(evenementRepository, defaultSpecification, cacheNameProvider, cacheManager);
        this.evenementRepository = evenementRepository;
        this.semestreRepository = semestreRepository;
        this.niveauRepository = niveauRepository;
        this.cacheManager = cacheManager;
        this.entityManager = entityManager;
    }

    @Override
    protected Evenement createRelationships(Evenement evenement, EvenementDtoRequest dto) {
        if (dto.getSemestreId() != null) {
            Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
            evenement.setSemestre(semestre);
        } else {
            evenement.setNiveau(null);
        }

        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            evenement.setNiveau(niveau);
        } else {
            evenement.setNiveau(null);
        }

        return evenement;
    }

    @Override
    protected Evenement updateRelationships(Evenement evenement, EvenementDtoRequest dto) {
        if (dto.getSemestreId() != null) {
            Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
            evenement.setSemestre(semestre);
        } else {
            evenement.setNiveau(null);
        }

        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            evenement.setNiveau(niveau);
        } else {
            evenement.setNiveau(null);
        }

        return evenement;
    }
    @Override
    public EvenementDtoResponse archive(Long id) {
        return null;
    }


//
//        // ✅ Archive avec éviction du cache liste + id
//        @Override
//        @Caching(evict = {
//                @CacheEvict(value = "evenementList", allEntries = true),
//                @CacheEvict(value = "evenement", key = "#id")
//        })
//        public EvenementDtoResponse archive(Long id) {
//            var evenement = getEntityById(id);
//            evenement.setArchive(true);
//            return MapperService.mapToDtoResponse(evenementRepository.save(evenement), EvenementDtoResponse.class);
//        }
}

