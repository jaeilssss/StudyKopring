package com.market.studyboardkt.user.application.dto

import com.market.studyboardkt.user.application.dto.response.UserInfoResponseDto
import com.market.studyboardkt.user.domain.entity.User

fun User.toUserInfoResponseDto(): UserInfoResponseDto = UserInfoResponseDto(
    name = name,
    nickName = nickName,
    email = email,
    address = address,
    birthDay = birthDay
)