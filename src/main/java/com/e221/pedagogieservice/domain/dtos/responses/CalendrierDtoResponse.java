package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendrierDtoResponse {
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private Statut statut;
    private boolean archive;
    private NiveauDtoResponse niveau;
    private SemestreDtoResponse semestre;
    @Builder.Default
    private List<EvenementDtoResponse> evenements = new ArrayList<>();
}
