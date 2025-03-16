package com.market.studyboardkt.user.presentation.dto

import com.market.studyboardkt.user.application.dto.request.SignUpDto

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val birthDay: String,
    val nickName: String,
    val address: String
) {
    fun toSignUpDto() = SignUpDto(
        name, email, password, phoneNumber, birthDay, nickName, address
    )
}