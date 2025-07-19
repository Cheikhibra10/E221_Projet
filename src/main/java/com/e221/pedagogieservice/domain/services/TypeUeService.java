package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.TypeUeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.requests.UeDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.TypeUeDtoResponse;
import com.e221.pedagogieservice.domain.dtos.responses.UeDtoResponse;
import com.e221.pedagogieservice.domain.models.TypeUe;
import com.e221.pedagogieservice.domain.models.UE;

public interface TypeUeService extends DefaultService<TypeUe, TypeUeDtoRequest, TypeUeDtoResponse> {
}
