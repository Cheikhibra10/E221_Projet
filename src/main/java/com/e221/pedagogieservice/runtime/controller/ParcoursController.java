package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.ParcoursDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ParcoursDtoResponse;
import com.e221.pedagogieservice.domain.models.Parcours;
import com.e221.pedagogieservice.domain.services.ParcoursService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/parcours")
@Tag(name = "Parcours", description = "Gestion des parcours")
public class ParcoursController extends GenericCrudController<
        Parcours,
        ParcoursDtoRequest,
        ParcoursDtoResponse> {

    public ParcoursController(ParcoursService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Parcours.class;
    }
}

