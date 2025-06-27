package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.repositories.EvenementRepository;
import com.e221.pedagogieservice.domain.services.EvenementService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
public class EvenementServiceImp extends DefaultServiceImp<Evenement, EvenementDtoRequest, EvenementDtoResponse> implements EvenementService {
    private final EvenementRepository evenementRepository;

    public EvenementServiceImp(EvenementRepository evenementRepository, DefaultSpecification<Evenement> defaultSpecification) {
        super(evenementRepository, defaultSpecification);
        this.evenementRepository = evenementRepository;
    }


    @Override
    public EvenementDtoResponse archive(Long id) {
        var evenement = getEntityById(id);
        return MapperService.mapToDtoResponse(evenementRepository.save(evenement), EvenementDtoResponse.class);
    }

    }
