package com.e221.pedagogieservice;

import com.e221.pedagogieservice.runtime.config.CacheTtlProperties;
import com.e221.pedagogieservice.runtime.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
@EnableConfigurationProperties(CacheTtlProperties.class)
public class PedagogieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedagogieServiceApplication.class, args);
	}
}
