package com.market.studyboardkt.user.domain

import com.market.studyboardkt.user.domain.dto.response.JwtTokenResponse

interface UserDomainService {
    fun isExistedEmail(email: String)

}