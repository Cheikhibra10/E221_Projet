package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SousClasseDtoRequest {
    private Long id;
    private String libelle;
    private Integer nbrEleve;
    private Boolean etat;
    private Boolean archive;
    private ClasseDtoRequest classe;
}
