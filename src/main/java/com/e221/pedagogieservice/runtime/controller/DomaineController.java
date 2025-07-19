package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.services.CivilityService;
import com.e221.pedagogieservice.domain.services.DomaineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@E221ApiVersion
@RequestMapping("/domaines")
@Tag(name = "Domaine", description = "Gestion des domaines de formation")
public class DomaineController extends GenericCrudController<
        Domaine,
        DomaineDtoRequest,
        DomaineDtoResponse> {

    public DomaineController(DomaineService domaineService) {
        super(domaineService);

    }

    @Override
    protected Class<?> getEntityClass() {
        return Domaine.class;
    }


}
