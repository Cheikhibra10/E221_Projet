package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.core.GenericRepository;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementCalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementCalendrierDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.models.*;
import com.e221.pedagogieservice.domain.repositories.*;
import com.e221.pedagogieservice.domain.services.EvenementCalendrierService;
import com.e221.pedagogieservice.domain.services.EvenementService;
import com.e221.pedagogieservice.domain.utils.DomainEntityHelper;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class EvenementCalendrierServiceImp extends DefaultServiceImp<EvenementCalendrier, EvenementCalendrierDtoRequest, EvenementCalendrierDtoResponse> implements EvenementCalendrierService {

    private final EvenementRepository evenementRepository;
    private final CalendrierRepository  calendrierRepository;
    private final SemestreRepository semestreRepository;

    public EvenementCalendrierServiceImp(GenericRepository<EvenementCalendrier> repository, DefaultSpecification<EvenementCalendrier> defaultSpecification, EvenementRepository evenementRepository, CalendrierRepository calendrierRepository, SemestreRepository semestreRepository) {
        super(repository, defaultSpecification);
        this.evenementRepository = evenementRepository;
        this.calendrierRepository = calendrierRepository;
        this.semestreRepository = semestreRepository;
    }

    @Override
    protected EvenementCalendrier updateRelationships(EvenementCalendrier evenementCalendrier, EvenementCalendrierDtoRequest dto) {

        if (dto.getEvenement() != null) {
            Evenement evenement = DomainEntityHelper.findOrUpdate(
                    evenementRepository,
                    dto.getEvenement(),
                    Evenement.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getEvenement().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            evenementCalendrier.setEvenement(evenement);
        } else {
            evenementCalendrier.setEvenement(null);
        }

        if (dto.getCalendrier() != null) {
            Calendrier calendrier = DomainEntityHelper.findOrUpdate(
                    calendrierRepository,
                    dto.getCalendrier(),
                    Calendrier.class,
                    existing -> existing.getLibelle().equalsIgnoreCase(dto.getCalendrier().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            evenementCalendrier.setCalendrier(calendrier);
        } else {
            evenementCalendrier.setCalendrier(null);
        }

        if (dto.getSemestre() != null) {
            Semestre semestre = DomainEntityHelper.findOrUpdate(
                    semestreRepository,
                    dto.getSemestre(),
                    Semestre.class,
                    existing ->
                            existing.getLibelle().equalsIgnoreCase(dto.getSemestre().getLibelle()),
                    MapperService::patchEntityFromDto
            );
            evenementCalendrier.setSemestre(semestre);
        } else {
            evenementCalendrier.setSemestre(null);
        }

        return evenementCalendrier;
    }
}
