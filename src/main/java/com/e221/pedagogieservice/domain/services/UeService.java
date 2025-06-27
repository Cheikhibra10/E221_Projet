package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.UeDtoResponse;
import com.e221.pedagogieservice.domain.models.UE;
import com.cheikh.commun.services.base.DefaultService;

public interface UeService extends DefaultService<UE, UeDtoRequest, UeDtoResponse> {
}
