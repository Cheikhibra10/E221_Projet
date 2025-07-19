package com.e221.pedagogieservice.domain.dtos.responses;


import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
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
public class UeDtoResponse {
    private Long id;
    private String code;
    private String libelle;
    private Statut statut;
    private boolean archive;
    @Builder.Default
    private List<MentionDtoResponse> mentions = new ArrayList<>();
}
