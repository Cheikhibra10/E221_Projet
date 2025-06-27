package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.UeDtoResponse;
import com.e221.pedagogieservice.domain.models.UE;
import com.e221.pedagogieservice.domain.services.UeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/ue")
@Tag(name = "UE", description = "Gestion des unités d'enseignement")
public class UeController extends GenericCrudController<
        UE,
        UeDtoRequest,
        UeDtoResponse> {

    public UeController(UeService ueService) {
        super(ueService);
    }

    @Override
    protected Class<?> getEntityClass() {
        return UE.class;
    }
}

