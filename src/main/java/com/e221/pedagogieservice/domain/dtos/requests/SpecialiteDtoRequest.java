package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpecialiteDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;

    @NotNull(message = "L'état est obligatoire.")
    private Statut statut;

    private Boolean archive;

    @NotNull(message = "La mention est obligatoire.")
    private MentionDtoRequest mention;
    private DomaineDtoRequest domaine;
    private List<NiveauDtoRequest> niveaux;

}
