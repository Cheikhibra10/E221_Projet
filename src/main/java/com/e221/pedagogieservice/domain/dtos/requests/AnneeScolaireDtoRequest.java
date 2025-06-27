package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnneeScolaireDtoRequest {
    private Long id;
    private String libelle;
    private Integer annee;
    private Boolean enCours;
    private Boolean archive;
}
