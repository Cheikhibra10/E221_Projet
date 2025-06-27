package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.PaysDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.PaysDtoResponse;
import com.e221.pedagogieservice.domain.models.Pays;
import com.cheikh.commun.services.base.DefaultService;

public interface PaysService extends DefaultService<Pays, PaysDtoRequest, PaysDtoResponse> {
}
