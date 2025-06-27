package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.ModuleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ModuleDtoResponse;
import com.e221.pedagogieservice.domain.models.Module;
import com.e221.pedagogieservice.domain.services.ModuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/modules")
@Tag(name = "Module", description = "Gestion des modules de formation")
public class ModuleController extends GenericCrudController<
        Module,
        ModuleDtoRequest,
        ModuleDtoResponse> {

    public ModuleController(ModuleService moduleService) {
        super(moduleService);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Module.class;
    }
}
