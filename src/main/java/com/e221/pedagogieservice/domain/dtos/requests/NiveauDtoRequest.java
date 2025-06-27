package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NiveauDtoRequest {
    private Long id;
    private String libelle;
    private Integer niveau;
    private Boolean etat;
    private Boolean archive;
    private CycleDtoRequest cycle;
    private ParcoursDtoRequest parcours;
}
