package com.market.studyboardkt.category.application

import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto
import com.market.studyboardkt.category.application.dto.response.CategoryListResponseDto

interface CategoryService {
    fun getCategoryList(): CategoryListResponseDto
    fun registerCategory(request: RegisterCategoryDto)
}