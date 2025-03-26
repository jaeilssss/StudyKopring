package com.market.studyboardkt.category.domain.repository

import com.market.studyboardkt.category.domain.entity.Category
import java.util.Optional

interface CategoryRepository {
    fun findById(id: Long): Optional<Category>
    fun save(category: Category): Optional<Category>
}