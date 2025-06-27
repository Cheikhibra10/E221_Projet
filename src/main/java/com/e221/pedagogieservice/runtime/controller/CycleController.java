package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.CycleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.Cycle;
import com.e221.pedagogieservice.domain.services.CycleService;
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
@RequestMapping("/cycles")
@Tag(name = "Cycle", description = "Gestion des cycles")
public class CycleController extends GenericCrudController<
        Cycle,
        CycleDtoRequest,
        CycleDtoResponse> {

    public CycleController(CycleService service) {
        super(service);
    }

    @Override
    protected Class<?> getEntityClass() {
        return Cycle.class;
    }
}

