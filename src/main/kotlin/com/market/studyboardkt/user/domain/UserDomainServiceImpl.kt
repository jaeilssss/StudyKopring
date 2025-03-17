package com.market.studyboardkt.user.domain

import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.UserErrorEnum
import com.market.studyboardkt.user.domain.dto.request.JwtProperties
import com.market.studyboardkt.user.domain.dto.response.JwtTokenResponse
import com.market.studyboardkt.user.domain.repository.UserRepository
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class UserDomainServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository
) : UserDomainService{

    override fun isExistedEmail(email: String) {
        userRepository.findByEmail(email).ifPresent {
            throw ErrorException(UserErrorEnum.IS_EXISTED_EMAIL.httpStatus, UserErrorEnum.IS_EXISTED_EMAIL.message)
        }
    }


}