package com.e221.pedagogieservice.domain.annotation.apiversionning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class E221ApiVersionConfiguration {
    @Bean
    public E221ApiVersionWebMvcRegistration apiVersionWebMvcRegistrations() {
        return new E221ApiVersionWebMvcRegistration();
    }
}
