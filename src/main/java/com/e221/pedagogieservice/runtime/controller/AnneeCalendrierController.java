package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.annotation.logging.Auditable;
import com.e221.pedagogieservice.domain.dtos.requests.AnneeScolaireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.AnneeScolaireDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ResponseDtoPaging;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;
import com.e221.pedagogieservice.domain.services.AnneeScolaireService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.e221.pedagogieservice.domain.constants.Constants.Auditable.*;

@RestController
@E221ApiVersion
@RequestMapping("/annee-scolaire")
@Tag(name = "Année Scolaire", description = "Gestion des années scolaires")
public class AnneeCalendrierController extends GenericCrudController<AnneeScolaire,AnneeScolaireDtoRequest,AnneeScolaireDtoResponse> {

    public AnneeCalendrierController(AnneeScolaireService anneeScolaireService){
        super(anneeScolaireService);
    }

    @Override
    protected Class<?> getEntityClass() {
        return AnneeScolaire.class;
    }
}
