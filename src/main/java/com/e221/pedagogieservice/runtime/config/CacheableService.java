package com.e221.pedagogieservice.runtime.config;

import java.util.List;

public interface CacheableService<R> {
    R getByIdWithCache(Long id);
    List<R> findAllWithCache();
    void evictCaches(Long id);
}