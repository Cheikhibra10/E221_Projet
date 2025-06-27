package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.SpecialiteDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.SpecialiteDtoResponse;
import com.e221.pedagogieservice.domain.models.Specialite;
import com.cheikh.commun.services.base.DefaultService;


public interface SpecialiteService extends DefaultService<Specialite, SpecialiteDtoRequest, SpecialiteDtoResponse>  {
}
