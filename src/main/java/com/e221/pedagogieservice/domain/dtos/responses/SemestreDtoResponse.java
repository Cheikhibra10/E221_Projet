package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SemestreDtoResponse {
    private Long id;
    private String libelle;
    private String duree;
    private Statut statut;
    private Boolean archive;
    private NiveauDtoResponse niveau;
}
