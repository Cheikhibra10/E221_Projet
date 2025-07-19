package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;
    @NotNull(message = "Le statut est obligatoire.")
    private Statut statut;
    @NotNull(message = "Le semestre est obligatoire.")
    private SemestreDtoRequest semestre;
    @NotNull(message = "Le niveau est obligatoire.")
    private NiveauDtoRequest niveau;
}
