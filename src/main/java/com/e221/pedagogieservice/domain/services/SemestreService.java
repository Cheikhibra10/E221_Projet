package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.SemestreDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SemestreDtoResponse;
import com.e221.pedagogieservice.domain.models.Semestre;
import com.cheikh.commun.services.base.DefaultService;


public interface SemestreService extends DefaultService<Semestre, SemestreDtoRequest, SemestreDtoResponse>  {
}
