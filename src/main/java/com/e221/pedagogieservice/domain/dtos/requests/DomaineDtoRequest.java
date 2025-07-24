package com.e221.pedagogieservice.domain.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomaineDtoRequest {
    @NotBlank(message = "Le libellé du domaine est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé du domaine doit contenir entre 2 et 100 caractères.")
    private String libelle;
}
