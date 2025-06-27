package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementCalendrierDtoResponse {
    private Long id;
    private String libelle;
    private Statut statut;
    private Date dateDebut;
    private Date dateFin;
    private boolean archive;
    private CalendrierDtoResponse calendrier;
    private EvenementDtoResponse evenement;
    private SemestreDtoResponse semestre;
}
