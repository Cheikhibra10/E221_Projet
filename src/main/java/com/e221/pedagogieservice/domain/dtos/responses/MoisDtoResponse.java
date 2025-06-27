package com.e221.pedagogieservice.domain.dtos.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MoisDtoResponse {
    private Long id;
    private String libelle;
}
