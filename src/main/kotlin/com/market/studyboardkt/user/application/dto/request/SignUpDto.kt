package com.market.studyboardkt.user.application.dto.request

import com.market.studyboardkt.user.domain.entity.User
import org.springframework.security.crypto.password.PasswordEncoder

data class SignUpDto(
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val birthDay: String,
    val nickName: String,
    val address: String
) {
    fun encodedPassword(encoder: PasswordEncoder): SignUpDto {
        return copy(password = encoder.encode(password))
    }

    fun toEntity(): User = User(
        name = name,
        email = email,
        password = password,
        phoneNumber = phoneNumber,
        birthDay = birthDay,
        nickName = nickName,
        address = address,
        isDeleted = false
    )
}