package com.e221.pedagogieservice.domain.dtos.requests;

import com.e221.pedagogieservice.domain.models.Niveau;
import com.e221.pedagogieservice.domain.models.Statut;
import com.e221.pedagogieservice.domain.models.TypeDocument;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DocumentDtoRequest {
    private Long id;
    private String libelle;
    private Statut statut;
    private TypeDocument typeDocument;
    private boolean archive;
    private List<Long> niveaux;
}
