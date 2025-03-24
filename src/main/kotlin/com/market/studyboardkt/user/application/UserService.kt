package com.market.studyboardkt.user.application

import com.market.studyboardkt.user.application.dto.request.LoginDto
import com.market.studyboardkt.user.application.dto.request.ModifyUserInfoDto
import com.market.studyboardkt.user.application.dto.request.SignUpDto
import com.market.studyboardkt.user.application.dto.response.LoginResponseDto
import com.market.studyboardkt.user.application.dto.response.UserInfoResponseDto
import com.market.studyboardkt.user.domain.dto.response.JwtTokenResponse

interface UserService {
    fun signUp(request: SignUpDto)
    fun login(request: LoginDto): LoginResponseDto
    fun getUserInfo(userId: Long): UserInfoResponseDto
    fun renewJwtToken(refreshToken: String): LoginResponseDto
    fun deleteUser(userId: Long)
    fun modifyUserInfo(request: ModifyUserInfoDto)
}