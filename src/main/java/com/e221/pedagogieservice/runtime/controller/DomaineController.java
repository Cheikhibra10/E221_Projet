package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.core.GenericCrudController;
import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.logging.Auditable;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.services.CivilityService;
import com.e221.pedagogieservice.domain.services.DomaineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @Override
    @PreAuthorize("hasRole('client_admin')")
    @PostMapping
    public ResponseEntity<DomaineDtoResponse> create(@Valid @RequestBody DomaineDtoRequest dto) {
        return super.create(dto);
    }

    @Override
    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/{id}")
    public ResponseEntity<DomaineDtoResponse> update(@PathVariable Long id, @RequestBody DomaineDtoRequest dto) {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasRole('client_user')")
    @GetMapping
    public ResponseEntity<PageResponse<DomaineDtoResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return super.findAll(page, size);
    }

//    @GetMapping
//    public ResponseEntity<PageResponse<DomaineDtoResponse>> findAllArchived(
//            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
//            @RequestParam(name = "size", defaultValue = "10", required = false) int size
//    ) {
//        return super.(page, size);
//    }
    @Operation(summary = "Obtenir une entité par ID", responses = {
            @ApiResponse(responseCode = "200", description = "Trouvée"),
            @ApiResponse(responseCode = "404", description = "Non trouvée")
    })
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/{id}")
    public ResponseEntity<DomaineDtoResponse> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Operation(summary = "Supprimer/archiver une entité", responses = {
            @ApiResponse(responseCode = "200", description = "Supprimée ou archivée"),
            @ApiResponse(responseCode = "404", description = "Non trouvée")
    })
    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<DomaineDtoResponse> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @Operation(summary = "Restaurer une entité archivée", responses = {
            @ApiResponse(responseCode = "200", description = "Entité restaurée avec succès"),
            @ApiResponse(responseCode = "404", description = "Entité non trouvée")
    })
    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/{id}/restore")
    public ResponseEntity<DomaineDtoResponse> restore(@PathVariable Long id) {
        return super.restore(id);
    }

    @Operation(summary = "Mettre à jour partiellement une entité", responses = {
            @ApiResponse(responseCode = "200", description = "Mise à jour partielle avec succès"),
            @ApiResponse(responseCode = "404", description = "Entité non trouvée")
    })
    @PreAuthorize("hasRole('client_admin')")
    @PatchMapping("/{id}")
    public ResponseEntity<DomaineDtoResponse> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return super.patch(id,fields);
    }

}

