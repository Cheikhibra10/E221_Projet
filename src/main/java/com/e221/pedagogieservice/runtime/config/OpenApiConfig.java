package com.e221.pedagogieservice.runtime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi specification - Cheikh",
                version = "1.0",
                description = "OpenApi documentation for Spring Boot app",
                contact = @Contact(
                        name = "Cheikh",
                        email = "contact@cheikhcoding.com",
                        url = "https://cheikhcoding.com/course"
                ),
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://aliboucoding.com/course"
                )
        }
)
public class OpenApiConfig {
}
