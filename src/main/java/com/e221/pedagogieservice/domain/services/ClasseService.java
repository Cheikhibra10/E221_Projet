package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.ClasseDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ClasseDtoResponse;
import com.e221.pedagogieservice.domain.models.Classe;

import java.util.List;


public interface ClasseService extends DefaultService<Classe, ClasseDtoRequest, ClasseDtoResponse> {

}
