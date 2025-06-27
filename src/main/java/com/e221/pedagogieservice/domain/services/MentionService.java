package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.MentionDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MentionDtoResponse;
import com.e221.pedagogieservice.domain.models.Mention;
import com.cheikh.commun.services.base.DefaultService;

import java.util.List;


public interface MentionService extends DefaultService<Mention, MentionDtoRequest, MentionDtoResponse>  {
}
