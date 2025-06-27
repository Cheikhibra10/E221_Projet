package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.MoisDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MoisDtoResponse;
import com.e221.pedagogieservice.domain.models.Mois;
import com.e221.pedagogieservice.domain.services.MoisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/mois")
@Tag(name = "Mois", description = "Gestion des mois")
public class MoisController extends GenericCrudController<
        Mois,
        MoisDtoRequest,
        MoisDtoResponse> {

    public MoisController(MoisService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Mois.class;
    }
}
