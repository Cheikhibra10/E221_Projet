package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CivilityDtoRequest {

    private Long id;
    private String libelle;
    private Statut statut;
    private boolean archive;

}
