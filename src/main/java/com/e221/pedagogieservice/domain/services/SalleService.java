package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.SalleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.SalleDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;
import com.e221.pedagogieservice.domain.models.Salle;

public interface SalleService extends DefaultService<Salle, SalleDtoRequest, SalleDtoResponse> {

}

