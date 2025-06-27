package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.MoisDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.MoisDtoResponse;
import com.e221.pedagogieservice.domain.models.Mois;
import com.cheikh.commun.services.base.DefaultService;

public interface MoisService extends DefaultService<Mois, MoisDtoRequest, MoisDtoResponse>{
}
