package com.market.studyboardkt.setting.config

import com.market.studyboardkt.user.domain.dto.request.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(JwtProperties::class)
@Configuration
class JwtConfig {
}