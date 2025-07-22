package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Statut;
import com.e221.pedagogieservice.domain.models.UE;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ModuleDtoRequest {

    private Long id;

    @NotBlank(message = "Le code du module est obligatoire.")
    @Size(min = 2, max = 20, message = "Le code doit contenir entre 2 et 20 caractères.")
    private String code;

    @NotBlank(message = "Le libellé du module est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;
    private Statut statut;
    private boolean archive;
    private List<Long> ues;
}
