package com.market.studyboardkt.user.domain.dto.response

data class JwtTokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
}