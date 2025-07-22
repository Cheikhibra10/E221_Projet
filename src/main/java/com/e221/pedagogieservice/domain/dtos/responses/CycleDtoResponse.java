package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CycleDtoResponse {
    private Long id;
    private String cycle;
    private String duree;
    private int taux_horaire;
    private Statut statut;
    private boolean archive;
}
