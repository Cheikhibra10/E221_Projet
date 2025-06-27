package com.e221.pedagogieservice.domain.mapper;

import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.models.Mention;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MentionMapper {
    MentionDtoResponse toMentionDtoResponse(Mention mention);
    Mention toMentionEntity(MentionDtoResponse mentionDtoResponse);
}
