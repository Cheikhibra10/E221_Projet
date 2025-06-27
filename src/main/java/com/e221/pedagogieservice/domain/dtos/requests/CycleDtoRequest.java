package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CycleDtoRequest {
    private Long id;
    private String cycle;
    private Boolean etat;
    private Boolean archive;
}
