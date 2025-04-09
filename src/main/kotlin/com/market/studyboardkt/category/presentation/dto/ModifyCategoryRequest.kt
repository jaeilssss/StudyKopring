package com.market.studyboardkt.category.presentation.dto

import com.market.studyboardkt.category.application.dto.request.ModifyCategoryDto

class ModifyCategoryRequest(
    val categoryId: Long,
    val name: String,
    val parentId: Long? = null
) {
    fun toDto(): ModifyCategoryDto =
        ModifyCategoryDto(
            categoryId = categoryId,
            name = name,
            parentId = parentId
        )
}