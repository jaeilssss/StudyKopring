package com.market.studyboardkt.category.application.dto.response

import com.market.studyboardkt.category.domain.entity.Category


class CategoryResponseDto(
    val id: Long,
    val name: String,
    val children: List<CategoryResponseDto>?
) {

}