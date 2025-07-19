package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialiteDtoResponse {
    private Long id;
    private String libelle;
    private boolean etat;
    private boolean archive;
    private MentionDtoResponse mention;
    private DomaineDtoResponse domaine;
    @Builder.Default
    private List<NiveauDtoResponse> niveaux = new ArrayList<>();
}
