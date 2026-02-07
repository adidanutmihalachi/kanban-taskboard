package com.adi.taskboard.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TaskBoard API")
                        .version("0.0.1")
                        .description("API REST para gestión de tableros Kanban con Spring Boot y arquitectura hexagonal")
                        .contact(new Contact()
                                .name("Adi Danut Mihalachi")
                                .email("adidanutmihalachi@gmail.com")
                                .url("https://github.com/adidanutmihalachi"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo"),
                        new Server()
                                .url("https://api.tudominio.com")
                                .description("Servidor de producción")
                ));
    }
}
