package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.HoraireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.HoraireDtoResponse;
import com.e221.pedagogieservice.domain.models.Horaire;
import com.cheikh.commun.services.base.DefaultService;

public interface HoraireService extends DefaultService<Horaire, HoraireDtoRequest, HoraireDtoResponse> {
}
