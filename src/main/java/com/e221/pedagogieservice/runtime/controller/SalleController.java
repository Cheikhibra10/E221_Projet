package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SalleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SalleDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.models.Salle;
import com.e221.pedagogieservice.domain.services.DomaineService;
import com.e221.pedagogieservice.domain.services.SalleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/salles")
@Tag(name = "Salle", description = "Gestion des salles de formation")
public class SalleController extends GenericCrudController<
        Salle,
        SalleDtoRequest,
        SalleDtoResponse> {

    public SalleController(SalleService salleService) {
        super(salleService);

    }

    @Override
    protected Class<?> getEntityClass() {
        return Salle.class;
    }


}