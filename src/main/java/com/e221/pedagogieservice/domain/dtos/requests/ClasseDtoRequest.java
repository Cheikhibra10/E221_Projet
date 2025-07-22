package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClasseDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 3, max = 100, message = "Le libellé doit contenir entre 3 et 100 caractères.")
    private String libelle;
    private String code;
    private Statut statut;
    private boolean archive;

    @Valid
    @NotNull(message = "La domaine est obligatoire.")
    private Long domaineId;

    @Valid
    @NotNull(message = "Le niveau est obligatoire.")
    private Long niveauId;

    @Valid
    @NotNull(message = "La spécialité est obligatoire.")
    private Long specialiteId;
}
