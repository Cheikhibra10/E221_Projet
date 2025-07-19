package com.e221.pedagogieservice.domain.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaysDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé du pays est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;

    private Boolean archive;
}
