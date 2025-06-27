package com.e221.pedagogieservice.domain.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpecialiteDtoRequest {
    private Long id;
    private String libelle;
    private String num;
    private Boolean etat;
    private Boolean archive;
    private MentionDtoRequest mention;
}
