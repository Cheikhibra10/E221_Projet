package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.CivilityDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CivilityDtoResponse;
import com.e221.pedagogieservice.domain.models.Civility;

public interface CivilityService extends DefaultService<Civility, CivilityDtoRequest, CivilityDtoResponse> {

}
