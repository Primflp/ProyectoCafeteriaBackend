package com.cafeteria.ms_producto.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cafeteria")
                        .version("2.0")
                        .description("Documentacion de la API para el sistema de cafeteria"));
    }

    @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("1. Versión 1.0 (Privada / Básica)")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiV2() {
        return GroupedOpenApi.builder()
                .group("2. Versión 2.0 (Completa / Pública)")
                .pathsToMatch("/api/v2/**")
                .build();
    }
}