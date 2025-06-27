package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.dtos.responses.SemestreDtoResponse;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.e221.pedagogieservice.domain.repositories.SemestreRepository;
import com.e221.pedagogieservice.domain.services.SemestreService;
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
public class SemestreServiceImp extends DefaultServiceImp<Semestre, SemestreDtoRequest, SemestreDtoResponse> implements SemestreService {
    private final SemestreRepository repository;

    public SemestreServiceImp(SemestreRepository repository, DefaultSpecification<Semestre> defaultSpecification) {
        super(repository, defaultSpecification);
        this.repository = repository;
    }

    @Override
    public SemestreDtoResponse archive(Long id) {
        var entity = getEntityById(id);
        entity.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(entity), SemestreDtoResponse.class);
    }


}
