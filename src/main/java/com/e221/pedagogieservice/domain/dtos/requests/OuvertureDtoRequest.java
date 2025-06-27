package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OuvertureDtoRequest {
    private Long id;
    private String libelle;
    private Boolean statut;
    private Date dateDebut;
    private Date dateFin;
    private Boolean archive;
    private AnneeScolaireDtoRequest anneeScolaire;
}
