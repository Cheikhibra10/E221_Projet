package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.CalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.CalendrierDtoResponse;
import com.e221.pedagogieservice.domain.models.Calendrier;

public interface CalendrierService extends DefaultService<Calendrier, CalendrierDtoRequest, CalendrierDtoResponse> {
}
