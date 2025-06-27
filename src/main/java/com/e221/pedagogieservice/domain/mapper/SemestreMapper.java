package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.SemestreDtoResponse;
import com.e221.pedagogieservice.domain.models.Semestre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SemestreMapper {
    SemestreDtoResponse toSemestreDtoResponse(Semestre semestre);
    Semestre toSemestreEntity(SemestreDtoResponse semestreDtoResponse);
}
