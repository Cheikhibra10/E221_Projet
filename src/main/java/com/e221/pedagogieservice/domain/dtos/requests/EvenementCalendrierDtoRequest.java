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
public class EvenementCalendrierDtoRequest {

    private Long id;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;

    @NotNull(message = "Le statut est obligatoire.")
    private Statut statut;

    @NotNull(message = "La date de début est obligatoire.")
    @FutureOrPresent(message = "La date de début doit être aujourd’hui ou dans le futur.")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire.")
    @Future(message = "La date de fin doit être dans le futur.")
    private LocalDate dateFin;

    @Column(nullable = false)
    private boolean archive;

    @NotNull(message = "Le calendrier associé est obligatoire.")
    private CalendrierDtoRequest calendrier;

    @NotNull(message = "L'événement associé est obligatoire.")
    private EvenementDtoRequest evenement;

    @NotNull(message = "Le semestre associé est obligatoire.")
    private SemestreDtoRequest semestre;
}
