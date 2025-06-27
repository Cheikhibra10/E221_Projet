package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.EvenementCalendrierDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.EvenementCalendrierDtoResponse;
import com.e221.pedagogieservice.domain.models.EvenementCalendrier;

public interface EvenementCalendrierService extends DefaultService<EvenementCalendrier, EvenementCalendrierDtoRequest, EvenementCalendrierDtoResponse> {
}
