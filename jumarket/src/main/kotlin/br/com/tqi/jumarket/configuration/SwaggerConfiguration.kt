package br.com.tqi.jumarket.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("JuMarket")
                    .description("Simulador de Autoatendimento com Spring Boot e Kotlin")
                    .version("1.0.0")
            )
    }

    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("jumarket-public")
            .pathsToMatch("/categories/**", "/products/**", "/checkout/**")
            .build()
    }
}