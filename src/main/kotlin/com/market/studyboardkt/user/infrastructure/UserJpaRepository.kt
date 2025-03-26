package com.market.studyboardkt.user.infrastructure

import com.market.studyboardkt.user.domain.entity.User
import com.market.studyboardkt.user.domain.repository.UserRepository
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : UserRepository, JpaRepository<User, Long> {
}