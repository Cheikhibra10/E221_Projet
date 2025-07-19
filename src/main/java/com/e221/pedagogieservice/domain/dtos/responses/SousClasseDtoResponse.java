package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SousClasseDtoResponse {
    private Long id;
    private String libelle;
    private Statut statut;
    private boolean archive;
    private ClasseDtoResponse classe;
    private SemestreDtoResponse semestre;

}
