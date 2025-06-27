package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomaineSpecialiteMapper {
    DomaineDtoResponse toDomaineDtoResponse(Domaine domaine);
    Domaine toDomaineEntity(DomaineDtoRequest domaineDtoRequest);
}
