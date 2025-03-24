package com.market.studyboardkt.user.application

import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.UserErrorEnum
import com.market.studyboardkt.setting.common.jwt.JwtProvider
import com.market.studyboardkt.user.application.dto.request.LoginDto
import com.market.studyboardkt.user.application.dto.request.ModifyUserInfoDto
import com.market.studyboardkt.user.application.dto.request.SignUpDto
import com.market.studyboardkt.user.application.dto.response.LoginResponseDto
import com.market.studyboardkt.user.application.dto.response.UserInfoResponseDto
import com.market.studyboardkt.user.application.dto.toUserInfoResponseDto
import com.market.studyboardkt.user.domain.UserDomainService
import com.market.studyboardkt.user.domain.entity.User
import com.market.studyboardkt.user.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userDomainService: UserDomainService,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun signUp(request: SignUpDto) {
        userDomainService.isExistedEmail(request.email)
        userRepository.save(
            request.encodedPassword(passwordEncoder).toEntity()
        ).orElseThrow {
            ErrorException(UserErrorEnum.NOT_FOUND_USER_INFO.httpStatus, UserErrorEnum.NOT_FOUND_USER_INFO.message)
        }
    }

    override fun login(request: LoginDto): LoginResponseDto {
        val user = userRepository.findByEmail(request.email).orElseThrow {
            ErrorException(UserErrorEnum.NOT_FOUND_USER_INFO.httpStatus, UserErrorEnum.NOT_FOUND_USER_INFO.message)
        }

        user.checkPassword(request.password, passwordEncoder)

        val tokenResponse = jwtProvider.createToken(user.id!!, user.email)
        return LoginResponseDto(tokenResponse.accessToken, tokenResponse.refreshToken)
    }

    override fun getUserInfo(userId: Long): UserInfoResponseDto {
        val user = findByUserIdOrThrow(userId)
        return user.toUserInfoResponseDto()
    }

    override fun renewJwtToken(refreshToken: String): LoginResponseDto {
        jwtProvider.validateToken(refreshToken)
        val user = findByUserIdOrThrow(jwtProvider.getUserIdByToken(refreshToken))
        val tokenResponse = jwtProvider.createToken(user.id!!, user.email)
        return LoginResponseDto(tokenResponse.accessToken,tokenResponse.refreshToken)
    }

    @Transactional
    override fun deleteUser(userId: Long) {
        val user = findByUserIdOrThrow(userId)
        user.delete()
    }

    @Transactional
    override fun modifyUserInfo(request: ModifyUserInfoDto) {
        val user = findByUserIdOrThrow(request.userId)
        user.modify(request.nickName, request.phoneNumber)
    }

    private fun findByUserIdOrThrow(userId: Long): User {
        return userRepository.findById(userId).orElseThrow {
            ErrorException(
                UserErrorEnum.NOT_FOUND_USER_INFO.httpStatus,
                UserErrorEnum.NOT_FOUND_USER_INFO.message
            )
        }
    }
}