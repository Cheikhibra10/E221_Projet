package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.e221.pedagogieservice.domain.services.EvenementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@E221ApiVersion
@RequestMapping("/evenements")
@Tag(name = "Événement", description = "Gestion des événements du calendrier")
public class EvenementController extends GenericCrudController<
        Evenement,
        EvenementDtoRequest,
        EvenementDtoResponse> {

    public EvenementController(EvenementService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Evenement.class;
    }
}

