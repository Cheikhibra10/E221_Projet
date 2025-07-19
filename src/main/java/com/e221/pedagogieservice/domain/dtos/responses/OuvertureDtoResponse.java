package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OuvertureDtoResponse {
    private Long id;
    private String libelle;
    private Statut statut;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean archive;
    private AnneeScolaireDtoResponse anneeScolaire;
}
