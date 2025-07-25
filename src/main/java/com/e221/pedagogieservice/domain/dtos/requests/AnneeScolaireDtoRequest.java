package com.e221.pedagogieservice.domain.dtos.requests;
import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnneeScolaireDtoRequest {
    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 3, max = 100, message = "Le libellé doit contenir entre 3 et 100 caractères.")
    private String libelle;
    @NotNull(message = "La date d'ouverture est obligatoire.")
    @FutureOrPresent(message = "La date d'ouverture doit être dans le présent ou le futur.")
    private LocalDate dateOuverture;
    @NotNull(message = "La date de fermeture est obligatoire.")
    @Future(message = "La date de fermeture doit être dans le futur.")
    private LocalDate dateFermeture;
    private Statut statut;
}
