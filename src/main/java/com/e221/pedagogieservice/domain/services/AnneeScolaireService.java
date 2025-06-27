package com.e221.pedagogieservice.domain.services;

import com.cheikh.commun.services.base.DefaultService;
import com.e221.pedagogieservice.domain.dtos.requests.AnneeScolaireDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.AnneeScolaireDtoResponse;
import com.e221.pedagogieservice.domain.models.AnneeScolaire;


public interface AnneeScolaireService extends DefaultService<AnneeScolaire, AnneeScolaireDtoRequest, AnneeScolaireDtoResponse> {
}
