package com.market.studyboardkt.user.application.dto.response

import com.market.studyboardkt.user.domain.entity.User

class UserInfoResponseDto(
    val name: String,
    val email: String,
    val nickName: String,
    val address: String,
    val birthDay: String
) {
    fun fromEntity(user: User) = UserInfoResponseDto(
        user.name, user.email, user.nickName, user.address, user.birthDay
    )
}