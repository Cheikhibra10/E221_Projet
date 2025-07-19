package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CycleDtoRequest {

    private Long id;

    @NotBlank(message = "Le nom du cycle est obligatoire.")
    @Size(min = 2, max = 100, message = "Le nom du cycle doit contenir entre 2 et 100 caractères.")
    private String cycle;
    private String duree;
    private int taux_horaire;
    private Statut statut;
    @NotNull(message = "Le champ 'archive' est obligatoire.")
    private Boolean archive;
}
