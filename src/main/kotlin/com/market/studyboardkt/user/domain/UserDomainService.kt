package com.market.studyboardkt.user.domain

import com.market.studyboardkt.user.domain.dto.response.JwtTokenResponse
import com.market.studyboardkt.user.domain.entity.User

interface UserDomainService {
    fun isExistedEmail(email: String)
}