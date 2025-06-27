package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CalendrierDtoResponse;
import com.e221.pedagogieservice.domain.models.Calendrier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalendrierMapper {
    CalendrierDtoResponse toCalendrierDtoResponse(Calendrier calendrier);
    Calendrier toCalendrierEntity(CalendrierDtoRequest CalendrierDtoRequest);
}
