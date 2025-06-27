package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementCalendrierDtoRequest {
    private Long id;
    private String libelle;
    private Statut statut;
    private Date dateDebut;
    private Date dateFin;
    private boolean archive;
    private CalendrierDtoRequest calendrier;
    private EvenementDtoRequest evenement;
    private SemestreDtoRequest semestre;
}
