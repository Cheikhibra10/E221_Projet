package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.OuvertureDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.OuvertureDtoResponse;
import com.e221.pedagogieservice.domain.models.Ouverture;
import com.cheikh.commun.services.base.DefaultService;


public interface OuvertureService extends DefaultService<Ouverture, OuvertureDtoRequest, OuvertureDtoResponse>  {
}
