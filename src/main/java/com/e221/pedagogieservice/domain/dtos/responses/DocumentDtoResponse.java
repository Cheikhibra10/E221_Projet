package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
import com.e221.pedagogieservice.domain.models.TypeDocument;
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
public class DocumentDtoResponse {
    private Long id;
    private String libelle;
    private Statut statut;
    private TypeDocument typeDocument;
    private boolean archive;
    @Builder.Default
    private List<NiveauDtoResponse> niveaux = new ArrayList<>();
}
