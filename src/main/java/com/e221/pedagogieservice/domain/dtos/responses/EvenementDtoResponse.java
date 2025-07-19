package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementDtoResponse {
    private Long id;
    private String libelle;

    private Statut statut;
    private SemestreDtoResponse semestre;
    private NiveauDtoResponse niveau;

}
