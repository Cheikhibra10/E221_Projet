package com.e221.pedagogieservice.domain.dtos.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ModuleDtoResponse {
    private Long id;
    private String code;
    private String libelle;
    private int numero;
    private boolean etat;
    private boolean archive;
}
