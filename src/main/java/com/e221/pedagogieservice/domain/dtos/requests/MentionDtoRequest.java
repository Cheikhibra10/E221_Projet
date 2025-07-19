package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MentionDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;

    @NotNull(message = "L'état est obligatoire.")
    private Statut statut;

    @NotNull(message = "Le champ archive est obligatoire.")
    private Boolean archive;

    @NotNull(message = "Le domaine est obligatoire.")
    private DomaineDtoRequest domaine;
}
