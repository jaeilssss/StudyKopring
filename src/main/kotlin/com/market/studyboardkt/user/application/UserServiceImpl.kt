package com.market.studyboardkt.user.application

import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.UserErrorEnum
import com.market.studyboardkt.setting.common.jwt.JwtProvider
import com.market.studyboardkt.user.application.dto.request.LoginDto
import com.market.studyboardkt.user.application.dto.request.SignUpDto
import com.market.studyboardkt.user.application.dto.response.LoginResponseDto
import com.market.studyboardkt.user.domain.UserDomainService
import com.market.studyboardkt.user.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userDomainService: UserDomainService,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) : UserService {
    override fun signUp(request: SignUpDto) {
        userDomainService.isExistedEmail(request.email)
        userRepository.save(
            request.encodedPassword(passwordEncoder).toEntity()
        ).orElseThrow {
            ErrorException(UserErrorEnum.NOT_FOUND_USER_INFO.httpStatus, UserErrorEnum.NOT_FOUND_USER_INFO.message)
        }
    }

    override fun login(request: LoginDto): LoginResponseDto{
        val user = userRepository.findByEmail(request.email).orElseThrow {
            ErrorException(UserErrorEnum.NOT_FOUND_USER_INFO.httpStatus, UserErrorEnum.NOT_FOUND_USER_INFO.message)
        }

        user.checkPassword(request.password, passwordEncoder)

        val tokenResponse = jwtProvider.createToken(user.id, user.email)
        return LoginResponseDto(tokenResponse.accessToken, tokenResponse.refreshToken)

    }

}