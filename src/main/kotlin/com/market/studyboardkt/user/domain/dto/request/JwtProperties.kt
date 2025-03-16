package com.market.studyboardkt.user.domain.dto.request

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val headers: String,
    val secretKey : String,
    val expiration: Long,
    val refreshExpiration: Long
) {
}