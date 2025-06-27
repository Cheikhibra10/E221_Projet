package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomaineDtoResponse {
    private Long id;
    private String libelle;
    private boolean etat;
    private boolean archive;
}
