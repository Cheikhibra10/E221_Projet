package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClasseDtoRequest {
    private Long id;
    private String libelle;
    private Boolean etat;
    private Boolean archive;
    private HoraireDtoRequest horaire;
    private NiveauDtoRequest niveau;
    private SpecialiteDtoRequest specialite;
}
