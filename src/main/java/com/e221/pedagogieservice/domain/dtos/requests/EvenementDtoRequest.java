package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EvenementDtoRequest {
    private Long id;
    private String libelle;
    private Boolean statut;
}
