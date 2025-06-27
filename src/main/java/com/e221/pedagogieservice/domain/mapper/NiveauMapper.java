package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.models.Niveau;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NiveauMapper {
    NiveauDtoResponse toNiveauDtoResponse(Niveau niveau);
    Niveau toNiveauEntity(NiveauDtoResponse niveauDtoResponse);
}
