package com.e221.pedagogieservice.runtime.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // prise en charge de LocalDate, etc.
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // pour avoir des dates ISO
        return mapper;
    }
}
