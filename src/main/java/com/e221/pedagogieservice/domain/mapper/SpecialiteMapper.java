package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.models.Specialite;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialiteMapper {
    SpecialiteDtoResponse toSpecialiteDtoResponse(Specialite specialite);
    Specialite toSpecialiteEntity(SpecialiteDtoResponse specialiteDtoResponse);
}
