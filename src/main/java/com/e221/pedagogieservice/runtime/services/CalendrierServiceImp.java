package com.e221.pedagogieservice.runtime.services;


import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.*;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.CalendrierRepository;
import com.e221.pedagogieservice.domain.repositories.EvenementRepository;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.repositories.SemestreRepository;
import com.e221.pedagogieservice.domain.services.CalendrierService;
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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class CalendrierServiceImp extends DefaultServiceImp<Calendrier, CalendrierDtoRequest, CalendrierDtoResponse> implements CalendrierService {

    private final NiveauRepository niveauRepository;
    private final CalendrierRepository calendrierRepository;
    private final SemestreRepository semestreRepository;
    private final EvenementRepository evenementRepository;
    private final EntityManager entityManager;
    private final CacheNameProvider cacheNameProvider;
    private final CacheManager cacheManager;

    public CalendrierServiceImp(
            GenericRepository<Calendrier> repository,
            DefaultSpecification<Calendrier> defaultSpecification,
            NiveauRepository niveauRepository, CalendrierRepository calendrierRepository,
            CacheNameProvider cacheNameProvider,
            CacheManager cacheManager, SemestreRepository semestreRepository, EvenementRepository evenementRepository, EntityManager entityManager
    ) {
        super(repository, defaultSpecification,cacheNameProvider,cacheManager);
        this.niveauRepository = niveauRepository;
        this.calendrierRepository = calendrierRepository;
        this.semestreRepository = semestreRepository;
        this.evenementRepository = evenementRepository;
        this.entityManager = entityManager;
        this.cacheNameProvider = cacheNameProvider;
        this.cacheManager = cacheManager;
    }

    @Override
    protected Calendrier reloadWithRelationships(Long id) {
        return calendrierRepository.findByIdWithEvenements(id)
                .orElseThrow(() -> new EntityNotFoundException("Calendrier not found id=" + id));
    }

    @Override
    protected List<CalendrierDtoResponse> mapListToDto(List<Calendrier> entities) {
        return entities.stream()
                .map(this::mapCalendrierToDtoWithEvenements)
                .toList();
    }

    @Override
    protected CalendrierDtoResponse mapToDto(Calendrier calendrier) {
        return mapCalendrierToDtoWithEvenements(calendrier);
    }


    private CalendrierDtoResponse mapCalendrierToDtoWithEvenements(Calendrier calendrier) {
        CalendrierDtoResponse dto = MapperService.mapToDtoResponse(calendrier, CalendrierDtoResponse.class);
        List<EvenementDtoResponse> evenements = calendrier.getEvenementCalendriers().stream()
                .map(EvenementCalendrier::getEvenement)
                .filter(Objects::nonNull)
                .map(ev -> MapperService.mapToDtoResponse(ev, EvenementDtoResponse.class))
                .toList();
        dto.setEvenements(evenements);
        return dto;
    }



    @Override
    protected Calendrier createRelationships(Calendrier calendrier, CalendrierDtoRequest dto) {
        // Niveau
        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            calendrier.setNiveau(niveau);
        }

        // Semestre
        if (dto.getSemestreId() != null) {
            Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
            calendrier.setSemestre(semestre);
        }

        // Evenements
        if (dto.getEvenements() != null && !dto.getEvenements().isEmpty()) {
            List<EvenementCalendrier> links = dto.getEvenements().stream()
                    .map(evenementId -> {
                        Evenement evenement = evenementRepository.findById(evenementId)
                                .orElseThrow(() -> new EntityNotFoundException("Evenement not found id=" + evenementId));
                        EvenementCalendrier ns = new EvenementCalendrier();
                        ns.setCalendrier(calendrier);
                        ns.setEvenement(evenement);
                        return ns;
                    })
                    .toList();
            calendrier.getEvenementCalendriers().addAll(links);
        }

        return calendrier;
    }



    @Override
    protected Calendrier updateRelationships(Calendrier calendrier, CalendrierDtoRequest dto) {
        // Niveau
        if (dto.getNiveauId() != null) {
            Niveau niveau = DomainEntityHelper.findStrictById(niveauRepository, dto.getNiveauId(), Niveau.class);
            calendrier.setNiveau(niveau);
        } else {
            calendrier.setNiveau(null);
        }

        // Semestre
        if (dto.getSemestreId() != null) {
            Semestre semestre = DomainEntityHelper.findStrictById(semestreRepository, dto.getSemestreId(), Semestre.class);
            calendrier.setSemestre(semestre);
        } else {
            calendrier.setSemestre(null);
        }

        // Evenements
        if (dto.getEvenements() != null) { // only if caller included the field
            calendrier.getEvenementCalendriers().clear();
            if (!dto.getEvenements().isEmpty()) {
                List<EvenementCalendrier> links = dto.getEvenements().stream()
                        .map(evenementId -> {
                            Evenement evenement = evenementRepository.findById(evenementId)
                                    .orElseThrow(() -> new EntityNotFoundException("Evenement not found id=" + evenementId));
                            EvenementCalendrier ns = new EvenementCalendrier();
                            ns.setCalendrier(calendrier);
                            ns.setEvenement(evenement);
                            return ns;
                        })
                        .toList();
                calendrier.getEvenementCalendriers().addAll(links);
            }
        }

        return calendrier;
    }


    @Override
    public CalendrierDtoResponse archive(Long id) {
        return null;
    }
}
