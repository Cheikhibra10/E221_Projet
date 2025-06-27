package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.models.Cycle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CycleMapper {
    CycleDtoResponse toCycleDtoResponse(Cycle cycle);
    Cycle toCycleEntity(CycleDtoResponse cycleDtoResponse);
}
