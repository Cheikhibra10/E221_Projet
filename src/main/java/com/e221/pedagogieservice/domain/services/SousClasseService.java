package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.SousClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SousClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.SousClasse;
import com.cheikh.commun.services.base.DefaultService;

import java.util.List;


public interface SousClasseService extends DefaultService<SousClasse, SousClasseDtoRequest, SousClasseDtoResponse>  {

}
