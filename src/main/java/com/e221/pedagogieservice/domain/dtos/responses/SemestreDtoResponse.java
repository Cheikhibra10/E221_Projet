package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SemestreDtoResponse {
    private Long id;
    private String libelle;
    private int numero;
    private boolean etat;
    private boolean archive;
}
