package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.repositories.NiveauRepository;
import com.e221.pedagogieservice.domain.services.NiveauService;
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
public class NiveauServiceImp extends DefaultServiceImp<Niveau, NiveauDtoRequest, NiveauDtoResponse> implements NiveauService {
    private final NiveauRepository repository;

    public NiveauServiceImp(NiveauRepository repository, DefaultSpecification<Niveau> defaultSpecification) {
        super(repository, defaultSpecification);
        this.repository = repository;
    }


    @Override
    public NiveauDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), NiveauDtoResponse.class);
    }



}
