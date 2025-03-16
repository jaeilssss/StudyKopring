package com.market.studyboardkt.user.domain.repository

import com.market.studyboardkt.user.domain.entity.User
import java.util.Optional

interface UserRepository {
    fun findById(userId: Long) : Optional<User>
    fun findByEmail(email: String) : Optional<User>
    fun save(user: User): Optional<User>
}