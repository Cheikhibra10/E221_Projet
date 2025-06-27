package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.DomaineDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.ParcoursDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.DomaineDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.ParcoursDtoResponse;
import com.e221.pedagogieservice.domain.models.Domaine;
import com.e221.pedagogieservice.domain.models.Parcours;
import com.cheikh.commun.services.base.DefaultService;

public interface ParcoursService extends DefaultService<Parcours, ParcoursDtoRequest, ParcoursDtoResponse> {

}