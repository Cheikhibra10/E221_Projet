package com.e221.pedagogieservice.domain.dtos.requests;

import jakarta.validation.constraints.*;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomaineDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé du domaine est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé du domaine doit contenir entre 2 et 100 caractères.")
    private String libelle;

    @NotNull(message = "Le champ 'état' est obligatoire.")
    private Boolean etat;

    @NotNull(message = "Le champ 'archive' est obligatoire.")
    private Boolean archive;
}
