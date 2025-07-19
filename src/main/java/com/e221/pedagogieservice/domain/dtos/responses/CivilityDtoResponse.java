package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CivilityDtoResponse {

    private Long id;
    private String libelle;
    private Statut statut;
    private boolean archive;
}
