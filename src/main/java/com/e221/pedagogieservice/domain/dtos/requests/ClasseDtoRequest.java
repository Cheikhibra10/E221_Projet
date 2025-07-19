package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
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
    @NotNull(message = "Le champ 'archive' est obligatoire.")
    private Boolean archive;

    @Valid
    @NotNull(message = "La domaine est obligatoire.")
    private DomaineDtoRequest domaine;

    @Valid
    @NotNull(message = "Le niveau est obligatoire.")
    private NiveauDtoRequest niveau;

    @Valid
    @NotNull(message = "La spécialité est obligatoire.")
    private SpecialiteDtoRequest specialite;
}
