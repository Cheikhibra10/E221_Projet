package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.EvenementDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementDtoResponse;
import com.e221.pedagogieservice.domain.models.Evenement;
import com.cheikh.commun.services.base.DefaultService;

public interface EvenementService extends DefaultService<Evenement, EvenementDtoRequest, EvenementDtoResponse> {
}
