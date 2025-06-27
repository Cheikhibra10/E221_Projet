package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SousClasseDtoResponse {
    private Long id;
    private String libelle;
    private int nbrEleve;
    private boolean etat;
    private boolean archive;
    private ClasseDtoResponse classe;
}
