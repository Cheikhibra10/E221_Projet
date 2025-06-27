package com.e221.pedagogieservice.domain.services;

import com.e221.pedagogieservice.domain.dtos.requests.ModuleDtoRequest;
import com.e221.pedagogieservice.domain.dtos.responses.ModuleDtoResponse;
import com.e221.pedagogieservice.domain.models.Module;
import com.cheikh.commun.services.base.DefaultService;

public interface ModuleService extends DefaultService<Module, ModuleDtoRequest, ModuleDtoResponse> {

}
