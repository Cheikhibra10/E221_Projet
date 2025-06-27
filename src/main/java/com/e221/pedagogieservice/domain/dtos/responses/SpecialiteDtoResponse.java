package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpecialiteDtoResponse {
    private Long id;
    private String libelle;
    private String num;
    private boolean etat;
    private boolean archive;
    private MentionDtoResponse mention;
}
