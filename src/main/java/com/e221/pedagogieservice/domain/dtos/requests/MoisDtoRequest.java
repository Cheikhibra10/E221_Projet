package com.e221.pedagogieservice.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MoisDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé du mois est obligatoire.")
    @Size(min = 3, max = 30, message = "Le libellé du mois doit contenir entre 3 et 30 caractères.")
    private String libelle;
}
