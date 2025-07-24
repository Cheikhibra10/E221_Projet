package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CivilityDtoRequest {

    private String libelle;
    private Statut statut;

}
