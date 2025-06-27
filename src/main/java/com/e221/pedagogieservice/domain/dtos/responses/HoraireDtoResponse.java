package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HoraireDtoResponse {
    private Long id;
    private String libelle;
    private boolean archive;
}
