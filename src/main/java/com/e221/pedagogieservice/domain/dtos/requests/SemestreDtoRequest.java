package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SemestreDtoRequest {
    private Long id;
    private String libelle;
    private Integer numero;
    private Boolean etat;
    private Boolean archive;
}
