package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.HoraireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.HoraireDtoResponse;
import com.e221.pedagogieservice.domain.models.Horaire;
import com.e221.pedagogieservice.domain.services.HoraireService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@E221ApiVersion
@RequestMapping("/horaires")
@Tag(name = "Horaire", description = "Gestion des horaires")
public class HoraireController extends GenericCrudController<
        Horaire,
        HoraireDtoRequest,
        HoraireDtoResponse> {

    public HoraireController(HoraireService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Horaire.class;
    }
}
