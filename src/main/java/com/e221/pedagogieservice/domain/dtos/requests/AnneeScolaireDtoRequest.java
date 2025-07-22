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
    private Long id;
    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 3, max = 100, message = "Le libellé doit contenir entre 3 et 100 caractères.")
    private String libelle;
    private LocalDate dateOuverture;
    private LocalDate dateFermeture;
    private Statut statut;
    private boolean archive;


}
