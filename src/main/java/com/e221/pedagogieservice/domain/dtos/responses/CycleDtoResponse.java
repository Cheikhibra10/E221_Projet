package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CycleDtoResponse {
    private Long id;
    private String cycle;
    private Boolean etat;
    private Boolean archive;
}
