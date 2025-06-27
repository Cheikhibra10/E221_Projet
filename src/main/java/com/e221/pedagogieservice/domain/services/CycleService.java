package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.CycleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CycleDtoResponse;
import com.e221.pedagogieservice.domain.models.Cycle;


public interface CycleService extends DefaultService<Cycle, CycleDtoRequest, CycleDtoResponse> {

}
