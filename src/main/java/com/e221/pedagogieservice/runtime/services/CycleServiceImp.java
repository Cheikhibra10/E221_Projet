package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.CycleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Cycle;
import com.e221.pedagogieservice.domain.repositories.CycleRepository;
import com.e221.pedagogieservice.domain.services.CycleService;
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
public class CycleServiceImp extends DefaultServiceImp<Cycle, CycleDtoRequest, CycleDtoResponse> implements CycleService {
    private final CycleRepository repository;

    public CycleServiceImp(CycleRepository repository, DefaultSpecification<Cycle> defaultSpecification) {
        super(repository, defaultSpecification);
        this.repository = repository;
    }

    @Override
    public CycleDtoResponse archive(Long id) {
        var cycle = getEntityById(id);
        cycle.setArchive(true);
        return MapperService.mapToDtoResponse(repository.save(cycle), CycleDtoResponse.class);
    }

}
