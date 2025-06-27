package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClasseMapper {
    ClasseDtoResponse toClasseDtoResponse(Classe classe);
    Classe toClasseEntity(ClasseDtoResponse classeDtoResponse);
}
