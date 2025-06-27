package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.NiveauDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.NiveauDtoResponse;
import com.e221.pedagogieservice.domain.models.Niveau;
import com.cheikh.commun.services.base.DefaultService;

import java.util.List;


public interface NiveauService extends DefaultService<Niveau, NiveauDtoRequest, NiveauDtoResponse>  {

}
