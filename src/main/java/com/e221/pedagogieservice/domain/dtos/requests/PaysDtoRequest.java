package com.e221.pedagogieservice.domain.dtos.requests;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PaysDtoRequest {
    private Long id;
    private String libelle;
    private Boolean archive;
}
