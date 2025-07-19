package com.e221.pedagogieservice.runtime.config;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cache.Cache;
import org.springframework.lang.NonNull;

import java.util.concurrent.Callable;

public class InstrumentedCache implements Cache {

    private final Cache delegate;
    private final Counter gets;
    private final Counter puts;
    private final Counter evictions;

    public InstrumentedCache(Cache delegate, MeterRegistry meterRegistry) {
        this.delegate = delegate;
        String cacheName = delegate.getName();

        this.gets = Counter.builder("cache_manual_gets_total")
                .tag("cache", cacheName)
                .description("Nombre total de gets dans le cache " + cacheName)
                .register(meterRegistry);

        this.puts = Counter.builder("cache_manual_puts_total")
                .tag("cache", cacheName)
                .description("Nombre total de puts dans le cache " + cacheName)
                .register(meterRegistry);

        this.evictions = Counter.builder("cache_manual_evictions_total")
                .tag("cache", cacheName)
                .description("Nombre total d'évictions dans le cache " + cacheName)
                .register(meterRegistry);
    }

    @Override
    @NonNull
    public String getName() {
        return delegate.getName();
    }

    @Override
    @NonNull
    public Object getNativeCache() {
        return delegate.getNativeCache();
    }

    @Override
    @NonNull
    public ValueWrapper get(Object key) {
        gets.increment();
        return delegate.get(key);
    }

    @Override
    @NonNull
    public <T> T get(Object key, Class<T> type) {
        gets.increment();
        return delegate.get(key, type);
    }

    @Override
    @NonNull
    public <T> T get(Object key, Callable<T> valueLoader) {
        gets.increment();
        return delegate.get(key, valueLoader);
    }

    @Override
    @NonNull
    public void put(Object key, Object value) {
        puts.increment();
        delegate.put(key, value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        puts.increment();
        return delegate.putIfAbsent(key, value);
    }

    @Override
    public void evict(Object key) {
        evictions.increment();
        delegate.evict(key);
    }

    @Override
    public void clear() {
        evictions.increment();
        delegate.clear();
    }
}
