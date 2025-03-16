package com.market.studyboardkt.setting.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val securityRequired = SecurityRequirement().addList("Bearer Token")
        val apiKey: SecurityScheme = SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")
        return OpenAPI().components(Components()).info(apiInfo()).components(Components().addSecuritySchemes("Bearer Token", apiKey)).addSecurityItem(securityRequired)
    }

    private fun apiInfo(): Info = Info().title("API TEST").description("test").version("1.0.0")

}