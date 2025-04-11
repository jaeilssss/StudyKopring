package com.market.studyboardkt.category.domain.repository

import com.market.studyboardkt.category.domain.entity.Category
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface CategoryRepository {
    fun findById(id: Long): Optional<Category>
    fun save(category: Category): Optional<Category>
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.children WHERE c.parent IS NULL")
    fun findAllByParentIsNull(): List<Category>?
}