package com.e221.pedagogieservice.domain.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ParcoursDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé du parcours est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;

    @NotNull(message = "L'état du parcours est obligatoire.")
    private Boolean etat;

    private Boolean archive;
}
