package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementCalendrierDto {
    private Long id;
    private String libelle;
    private Date dateDebut;
    private Date dateFin;
    private Statut statut;
    private Boolean archive;
    private Long calendrierId;
    private Long evenementId;

}
