package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.UeDtoResponse;
import com.e221.pedagogieservice.domain.models.UE;
import com.e221.pedagogieservice.domain.repositories.UeRepository;
import com.e221.pedagogieservice.domain.services.UeService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class UEServiceImp extends DefaultServiceImp<UE, UeDtoRequest, UeDtoResponse> implements UeService {
    private final UeRepository ueRepository;

    public UEServiceImp(UeRepository ueRepository, DefaultSpecification<UE> defaultSpecification) {
        super(ueRepository, defaultSpecification);
        this.ueRepository = ueRepository;
    }

}
