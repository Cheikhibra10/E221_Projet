package com.e221.pedagogieservice.domain.dtos.responses;


import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.models.Statut;
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
public class ModuleDtoResponse {
    private Long id;
    private String code;
    private String libelle;
    private Statut statut;
    private boolean archive;
    @Builder.Default
    private List<UeDtoResponse> ues = new ArrayList<>();

}
