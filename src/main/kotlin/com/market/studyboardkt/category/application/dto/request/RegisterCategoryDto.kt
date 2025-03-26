package com.market.studyboardkt.category.application.dto.request

import com.market.studyboardkt.category.domain.entity.Category

class RegisterCategoryDto(
    val name: String,
    val parentCategory: Long?,

) {

    fun toEntity(parentCategory: Category?) = Category(
        name = name, parent = parentCategory
    )
}