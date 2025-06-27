package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NiveauDtoResponse {
    private Long id;
    private String libelle;
    private int niveau;
    private boolean etat;
    private boolean archive;
    private CycleDtoResponse cycle;
    private ParcoursDtoResponse parcours;
}
