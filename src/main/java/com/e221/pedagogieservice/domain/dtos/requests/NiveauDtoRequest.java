package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NiveauDtoRequest {

    @NotBlank(message = "Le libellé du niveau est obligatoire.")
    @Size(min = 2, max = 50, message = "Le libellé doit contenir entre 2 et 50 caractères.")
    private String libelle;
    private Statut statut;
    @NotNull(message = "Le cycle est obligatoire.")
    private Long cycleId;
}
