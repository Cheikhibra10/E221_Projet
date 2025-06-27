package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.services.NiveauService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@E221ApiVersion
@RequestMapping("/niveaux")
@Tag(name = "Niveau", description = "Gestion des niveaux d'enseignement")
public class NiveauController extends GenericCrudController<
        Niveau,
        NiveauDtoRequest,
        NiveauDtoResponse> {

    public NiveauController(NiveauService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Niveau.class;
    }
}

