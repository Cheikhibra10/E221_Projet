package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.dtos.responses.SemestreDtoResponse;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.e221.pedagogieservice.domain.services.SemestreService;
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
@RequestMapping("/semestres")
@Tag(name = "Semestre", description = "Gestion des semestres académiques")
public class SemestreController extends GenericCrudController<
        Semestre,
        SemestreDtoRequest,
        SemestreDtoResponse> {

    public SemestreController(SemestreService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Semestre.class;
    }
}

