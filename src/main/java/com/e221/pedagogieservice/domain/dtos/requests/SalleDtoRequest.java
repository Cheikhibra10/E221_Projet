package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalleDtoRequest {
    private Long id;
    private String numero;
    private String libelle;
    private int nbrPlace;
    private Statut statut;
    private boolean archive;

}
