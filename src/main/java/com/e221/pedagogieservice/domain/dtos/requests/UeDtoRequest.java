package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Mention;
import com.e221.pedagogieservice.domain.models.Statut;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UeDtoRequest {

    private Long id;

    @NotBlank(message = "Le code est obligatoire.")
    @Size(min = 1, max = 50, message = "Le code doit contenir entre 1 et 50 caractères.")
    private String code;

    @NotBlank(message = "Le libellé est obligatoire.")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères.")
    private String libelle;
    private Statut statut;
    private boolean archive;
    private List<Long> mentions;

}
