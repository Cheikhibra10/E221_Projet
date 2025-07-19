package com.e221.pedagogieservice.domain.mapper;

import com.cheikh.commun.services.MapperService;
import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.models.NiveauSpecialite;
import com.e221.pedagogieservice.domain.models.Specialite;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface SpecialiteMapper {

    SpecialiteDtoResponse toDto(Specialite entity);

    @AfterMapping
    default void mapNiveaux(Specialite entity, @MappingTarget SpecialiteDtoResponse dto) {
        if (entity.getNiveauxSpecialites() != null) {
            List<NiveauDtoResponse> niveaux = entity.getNiveauxSpecialites().stream()
                    .map(NiveauSpecialite::getNiveau)
                    .filter(Objects::nonNull)
                    .map(niv -> new NiveauDtoResponse(
                            niv.getId(),
                            niv.getLibelle(),
                            niv.getStatut(),
                            niv.isArchive(),
                            niv.getCycle() == null ? null : MapperService.mapToDtoResponse(niv.getCycle(), CycleDtoResponse.class)
                    ))
                    .toList();
            dto.setNiveaux(niveaux);
        }
    }
}
