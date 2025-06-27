package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendrierDtoRequest {
    private Long id;
    private String libelle;
    private Boolean statut;
    private Boolean archive;
    private NiveauDtoRequest niveau;
}
