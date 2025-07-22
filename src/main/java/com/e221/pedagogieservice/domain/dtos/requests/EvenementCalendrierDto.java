package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementCalendrierDto {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;



    private Statut statut;

    private Boolean archive;

    @NotNull(message = "L'identifiant du calendrier est obligatoire.")
    private Long calendrierId;

    @NotNull(message = "L'identifiant de l'événement est obligatoire.")
    private Long evenementId;
}
