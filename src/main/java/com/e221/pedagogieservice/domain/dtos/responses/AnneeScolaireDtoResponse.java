package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnneeScolaireDtoResponse {
    private Long id;
    private String libelle;
    private LocalDate dateOuverture;
    private LocalDate dateFermeture;
    private Statut statut;
    private boolean archive;
}
