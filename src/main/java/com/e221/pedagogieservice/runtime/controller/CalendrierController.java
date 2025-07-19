package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.config.AuditableUtil;
import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.logging.Auditable;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CalendrierDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SousClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.Calendrier;
import com.e221.pedagogieservice.runtime.services.CalendrierServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@E221ApiVersion
@RequestMapping("/calendrier")
@Tag(name = "Calendrier", description = "Gestion des calendrier de formation")
public class CalendrierController{

    public CalendrierController(CalendrierServiceImp service) {
        this.service = service;
    }

    protected Class<?> getEntityClass() {
        return Calendrier.class;
    }

    protected String audit(String action) {
        return AuditableUtil.build(action, getEntityClass());
    }

    private final CalendrierServiceImp service;
    @Operation(summary = "Créer une entity", responses = {
            @ApiResponse(responseCode = "201", description = "Créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @Auditable(value = "#{T(this).audit('create')}")
    @PostMapping
    public ResponseEntity<CalendrierDtoResponse> create(@Valid @RequestBody CalendrierDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Mettre à jour une entity", responses = {
            @ApiResponse(responseCode = "200", description = "Mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "entity non trouvée")
    })
    @Auditable(value = "#{T(this).audit('update')}")
    @PutMapping("/{id}")
    public ResponseEntity<CalendrierDtoResponse> update(@PathVariable Long id, @RequestBody CalendrierDtoRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Lister toutes les entities", responses = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée")
    })
    @Auditable(value = "#{T(this).audit('get_all')}")
    @GetMapping
    public ResponseEntity<PageResponse<CalendrierDtoResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(service.findAll(page,size));
    }

    @Operation(summary = "Obtenir une entity par ID", responses = {
            @ApiResponse(responseCode = "200", description = "Trouvée"),
            @ApiResponse(responseCode = "404", description = "Non trouvée")
    })
    @Auditable(value = "#{T(this).audit('get_by_id')}")
    @GetMapping("/{id}")
    public ResponseEntity<CalendrierDtoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Supprimer/archiver une entity", responses = {
            @ApiResponse(responseCode = "200", description = "Supprimée ou archivée"),
            @ApiResponse(responseCode = "404", description = "Non trouvée")
    })
    @Auditable(value = "#{T(this).audit('delete')}")
    @DeleteMapping("/{id}")
    public ResponseEntity<CalendrierDtoResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @Auditable(value = "#{T(this).audit('restore')}")
    @PutMapping("/{id}/restore")
    public ResponseEntity<CalendrierDtoResponse> restore(@PathVariable Long id) {
        return ResponseEntity.ok(service.restore(id));
    }

    @Operation(summary = "Mettre à jour partiellement une entité", responses = {
            @ApiResponse(responseCode = "200", description = "Mise à jour partielle avec succès"),
            @ApiResponse(responseCode = "404", description = "Entité non trouvée")
    })
    @Auditable(value = "#{T(this).audit('patch')}")
    @PatchMapping("/{id}")
    public ResponseEntity<CalendrierDtoResponse> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(service.patchFields(id, fields));
    }
}