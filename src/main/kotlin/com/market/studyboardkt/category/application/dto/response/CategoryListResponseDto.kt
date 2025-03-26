package com.market.studyboardkt.category.application.dto.response


class CategoryListResponseDto(
    val id: Long,
    val name: Long,
    val depth: Int,
    val children: List<CategoryListResponseDto>
) {


}