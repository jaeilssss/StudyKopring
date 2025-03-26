package com.market.studyboardkt.category.infrastructure.repository

import com.market.studyboardkt.category.domain.entity.Category
import com.market.studyboardkt.category.domain.repository.CategoryRepository
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : CategoryRepository, JpaRepository<Category, Long> {
}