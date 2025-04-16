package com.market.studyboardkt.category.application

import com.market.studyboardkt.category.application.dto.request.ModifyCategoryDto
import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto
import com.market.studyboardkt.category.application.dto.response.AllCategoryListResponseDto
import com.market.studyboardkt.category.application.dto.response.CategoryResponseDto

interface CategoryService {
    fun getCategoryList(): AllCategoryListResponseDto?
    fun registerCategory(request: RegisterCategoryDto)
    fun modifyCategory(request: ModifyCategoryDto)
    fun deleteCategory(categoryId: Long)
}