package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnneeScolaireDtoResponse {
    private Long id;
    private String libelle;
    private int annee;
    private boolean enCours;
    private boolean archive;
}
