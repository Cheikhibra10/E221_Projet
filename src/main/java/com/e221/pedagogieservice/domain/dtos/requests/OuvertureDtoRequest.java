package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OuvertureDtoRequest {
    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 3, max = 50, message = "Le libellé doit contenir entre 3 et 50 caractères.")
    private String libelle;

    private Statut statut;

    @NotNull(message = "La date de début est obligatoire.")
    @FutureOrPresent(message = "La date de début ne peut pas être dans le futur.")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire.")
    @Future(message = "La date de fin doit être aujourd’hui ou dans le futur.")
    private LocalDate dateFin;
    @NotNull(message = "L'année scolaire est obligatoire.")
    private Long anneeScolaireId;
}
