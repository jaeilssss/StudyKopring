package com.market.studyboardkt.user.infrastructure

import com.market.studyboardkt.user.domain.entity.User
import com.market.studyboardkt.user.domain.repository.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface UserJpaRepository : UserRepository, JpaRepository<User, Long> {
}