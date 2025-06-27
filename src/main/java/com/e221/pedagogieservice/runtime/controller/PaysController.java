package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.PaysDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.PaysDtoResponse;
import com.e221.pedagogieservice.domain.models.Pays;
import com.e221.pedagogieservice.domain.services.PaysService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/pays")
@Tag(name = "Pays", description = "Gestion des pays")
public class PaysController extends GenericCrudController<
        Pays,
        PaysDtoRequest,
        PaysDtoResponse> {

    public PaysController(PaysService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Pays.class;
    }
}
