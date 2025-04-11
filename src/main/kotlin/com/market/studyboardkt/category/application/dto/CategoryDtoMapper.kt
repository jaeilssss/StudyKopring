package com.market.studyboardkt.category.application.dto

import com.market.studyboardkt.category.application.dto.response.AllCategoryListResponseDto
import com.market.studyboardkt.category.application.dto.response.CategoryResponseDto
import com.market.studyboardkt.category.domain.entity.Category

fun Category.toCategoryResponseDto(): CategoryResponseDto = CategoryResponseDto(
    id = id!!,
    name = name,
    children = children?.let { it.stream().map { it.toCategoryResponseDto() }.toList() } ?: null
)
