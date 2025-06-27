package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementDtoResponse {
    private Long id;
    private String libelle;
    private boolean statut;
}
