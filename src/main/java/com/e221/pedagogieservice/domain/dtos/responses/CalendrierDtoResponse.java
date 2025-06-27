package com.e221.pedagogieservice.domain.dtos.responses;

import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CalendrierDtoResponse {
    private Long id;
    private String libelle;
    private boolean statut;
    private boolean archive;
    private NiveauDtoResponse niveau;
}
