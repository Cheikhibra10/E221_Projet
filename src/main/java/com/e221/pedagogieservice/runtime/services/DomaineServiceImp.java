package com.e221.pedagogieservice.runtime.services;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.repositories.DomaineRepository;
import com.e221.pedagogieservice.domain.services.DomaineService;
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
public class DomaineServiceImp extends DefaultServiceImp<Domaine, DomaineDtoRequest, DomaineDtoResponse> implements DomaineService {
    private final DomaineRepository domaineRepository;
    public DomaineServiceImp(DomaineRepository domaineRepository, DefaultSpecification<Domaine> defaultSpecification) {
        super(domaineRepository, defaultSpecification);
        this.domaineRepository = domaineRepository;
    }

    @Override
    public DomaineDtoResponse archive(Long id) {
        var domaine = getEntityById(id);
        domaine.setArchive(true);
        domaineRepository.save(domaine);
        return MapperService.mapToDtoResponse(domaine, DomaineDtoResponse.class);
    }


}
