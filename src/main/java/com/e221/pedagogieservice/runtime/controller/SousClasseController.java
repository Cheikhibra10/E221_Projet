package com.e221.pedagogieservice.runtime.controller;

import com.cheikh.commun.config.AuditableUtil;
import com.cheikh.commun.core.PageResponse;
import com.cheikh.commun.logging.Auditable;
import com.e221.pedagogieservice.domain.annotation.apiversionning.E221ApiVersion;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SousClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SousClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.runtime.services.ClasseServiceImp;
import com.e221.pedagogieservice.runtime.services.SousClasseServiceImp;
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
@RequestMapping("/sousclasses")
@Tag(name = "SousClasse", description = "Gestion des sousclasses")
public class SousClasseController {
    public SousClasseController(SousClasseServiceImp service) {
        this.service = service;
    }

    protected Class<?> getEntityClass() {
        return Classe.class;
    }

    protected String audit(String action) {
        return AuditableUtil.build(action, getEntityClass());
    }
    private final SousClasseServiceImp service;
    @Operation(summary = "Créer une entity", responses = {
            @ApiResponse(responseCode = "201", description = "Créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @Auditable(value = "#{T(this).audit('create')}")
    @PostMapping
    public ResponseEntity<SousClasseDtoResponse> create(@Valid @RequestBody SousClasseDtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Mettre à jour une entity", responses = {
            @ApiResponse(responseCode = "200", description = "Mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "entity non trouvée")
    })
    @Auditable(value = "#{T(this).audit('update')}")
    @PutMapping("/{id}")
    public ResponseEntity<SousClasseDtoResponse> update(@PathVariable Long id,@RequestBody SousClasseDtoRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Lister toutes les entities", responses = {
            @ApiResponse(responseCode = "200", description = "Liste récupérée")
    })
    @Auditable(value = "#{T(this).audit('get_all')}")
    @GetMapping
    public ResponseEntity<PageResponse<SousClasseDtoResponse>> findAll(
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
    public ResponseEntity<SousClasseDtoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Supprimer/archiver une entity", responses = {
            @ApiResponse(responseCode = "200", description = "Supprimée ou archivée"),
            @ApiResponse(responseCode = "404", description = "Non trouvée")
    })
    @Auditable(value = "#{T(this).audit('delete')}")
    @DeleteMapping("/{id}")
    public ResponseEntity<SousClasseDtoResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @Operation(summary = "Restaurer une entité archivée", responses = {
            @ApiResponse(responseCode = "200", description = "Entité restaurée avec succès"),
            @ApiResponse(responseCode = "404", description = "Entité non trouvée")
    })
    @Auditable(value = "#{T(this).audit('restore')}")
    @PutMapping("/{id}/restore")
    public ResponseEntity<SousClasseDtoResponse> restore(@PathVariable Long id) {
        return ResponseEntity.ok(service.restore(id));
    }

    @Operation(summary = "Mettre à jour partiellement une entité", responses = {
            @ApiResponse(responseCode = "200", description = "Mise à jour partielle avec succès"),
            @ApiResponse(responseCode = "404", description = "Entité non trouvée")
    })
    @Auditable(value = "#{T(this).audit('patch')}")
    @PatchMapping("/{id}")
    public ResponseEntity<SousClasseDtoResponse> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(service.patchFields(id, fields));
    }
}
