package com.market.studyboardkt.category.application.dto.response

import com.market.studyboardkt.category.domain.entity.Category

class AllCategoryListResponseDto(
    val categoryList: List<CategoryResponseDto>
){
    fun fromEntityList(categoryList: List<Category>) = categoryList.stream().map {

    }
}