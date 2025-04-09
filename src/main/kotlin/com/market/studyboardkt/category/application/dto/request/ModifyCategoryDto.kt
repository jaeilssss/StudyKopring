package com.market.studyboardkt.category.application.dto.request

class ModifyCategoryDto(
    val categoryId: Long,
    val name: String,
    val parentId: Long?
) {
}