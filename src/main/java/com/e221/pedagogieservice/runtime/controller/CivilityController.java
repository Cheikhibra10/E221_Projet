package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.CivilityDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CivilityDtoResponse;
import com.e221.pedagogieservice.domain.models.Civility;
import com.e221.pedagogieservice.domain.models.Salle;
import com.e221.pedagogieservice.domain.services.CivilityService;
import com.e221.pedagogieservice.domain.services.SalleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/civilities")
@Tag(name = "Civility", description = "Gestion des civilities de formation")
public class CivilityController extends GenericCrudController<
        Civility,
        CivilityDtoRequest,
        CivilityDtoResponse> {

    public CivilityController(CivilityService civilityService) {
        super(civilityService);

    }

    @Override
    protected Class<?> getEntityClass() {
        return Civility.class;
    }


}