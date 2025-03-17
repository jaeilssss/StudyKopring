package com.market.studyboardkt.setting.config

import com.market.studyboardkt.setting.common.jwt.JwtProvider
import com.market.studyboardkt.setting.filter.JwtAuthenticationFilter
import com.market.studyboardkt.setting.filter.JwtExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(val jwtProvider: JwtProvider) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .rememberMe { it.disable() }
            .formLogin{it.disable()}
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/v3/api-docs/**",   // OpenAPI 문서 JSON
                    "/swagger-ui/**",    // Swagger UI 정적 파일
                    "/user/signup",
                    "/user/login"
                ).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(JwtExceptionFilter(), JwtAuthenticationFilter::class.java)
            .build()
}

