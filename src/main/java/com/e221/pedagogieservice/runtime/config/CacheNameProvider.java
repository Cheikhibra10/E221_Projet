package com.e221.pedagogieservice.runtime.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheNameProvider {
    public String getListCacheName(Class<?> clazz) {
        return clazz.getSimpleName().replace("DtoResponse", "").toLowerCase() + "List";
    }

    public String getSingleCacheName(Class<?> clazz) {
        return clazz.getSimpleName().replace("DtoResponse", "").toLowerCase();
    }
}

