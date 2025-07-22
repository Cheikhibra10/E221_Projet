package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendrierDtoRequest {

    private Long id; // non validé ici


    @NotNull(message = "La date de début est obligatoire.")
    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur.")
    private LocalDate dateDebut;
    @NotNull(message = "La date de fin est obligatoire.")
    @Future(message = "La date de fin doit être dans le futur.")
    private LocalDate dateFin;
    private Statut statut;
    private boolean archive;
    @NotNull(message = "Le niveau est obligatoire.")
    private Long niveauId;
    @NotNull(message = "Le semestre est obligatoire.")
    private Long semestreId;
    private List<Long> evenements;
}
