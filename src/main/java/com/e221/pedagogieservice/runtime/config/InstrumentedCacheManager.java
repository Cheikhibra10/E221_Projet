package com.e221.pedagogieservice.runtime.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.stream.Collectors;

public class InstrumentedCacheManager implements CacheManager {

    private final CacheManager delegate;
    private final MeterRegistry meterRegistry;

    public InstrumentedCacheManager(CacheManager delegate, MeterRegistry meterRegistry) {
        this.delegate = delegate;
        this.meterRegistry = meterRegistry;
    }

    @Override
    @NonNull
    public Cache getCache(String name) {
        Cache cache = delegate.getCache(name);
        if (cache == null) {
            return null;
        }
        // Wrap cache avec l'instrumentation Micrometer
        return new InstrumentedCache(cache, meterRegistry);
    }

    @Override
    @NonNull
    public Collection<String> getCacheNames() {
        return delegate.getCacheNames();
    }
}
