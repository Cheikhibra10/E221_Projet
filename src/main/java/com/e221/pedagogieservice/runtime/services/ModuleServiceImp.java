package com.e221.pedagogieservice.runtime.services;

import com.e221.pedagogieservice.domain.dtos.requests.ModuleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ModuleDtoResponse;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.repositories.ModuleRepository;
import com.e221.pedagogieservice.domain.services.ModuleService;
import com.e221.pedagogieservice.runtime.services.base.DefaultServiceImp;
import com.e221.pedagogieservice.runtime.specifications.DefaultSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class ModuleServiceImp extends DefaultServiceImp<Module, ModuleDtoRequest, ModuleDtoResponse> implements ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleServiceImp(ModuleRepository moduleRepository, DefaultSpecification<Module> defaultSpecification) {
        super(moduleRepository, defaultSpecification);
        this.moduleRepository = moduleRepository;
    }

}
