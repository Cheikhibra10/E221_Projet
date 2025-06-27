package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.exceptions.EntityNotFoundException;
import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.constants.ErrorsMessages;
import com.e221.pedagogieservice.domain.dtos.requests.SousClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.dtos.responses.SousClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.SousClasse;
import com.e221.pedagogieservice.domain.repositories.SousClasseRepository;
import com.e221.pedagogieservice.domain.services.SousClasseService;
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
public class SousClasseServiceImp extends DefaultServiceImp<SousClasse, SousClasseDtoRequest, SousClasseDtoResponse> implements SousClasseService {
    private final SousClasseRepository repository;

    public SousClasseServiceImp(SousClasseRepository repository, DefaultSpecification<SousClasse> defaultSpecification) {
        super(repository, defaultSpecification);
        this.repository = repository;
    }

    @Override
    public SousClasseDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), SousClasseDtoResponse.class);
    }


}
