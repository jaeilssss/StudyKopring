package com.market.studyboardkt.category.presentation.dto

import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto

class RegisterCategoryRequest(
    val name: String,
    val parentCategory: Long? = null,

){

    fun toRegisterCategoryDto() = RegisterCategoryDto(name, parentCategory)
}