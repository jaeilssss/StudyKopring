package com.market.studyboardkt.category.presentation

import com.market.studyboardkt.category.application.CategoryService
import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto
import com.market.studyboardkt.category.application.dto.response.CategoryListResponseDto
import com.market.studyboardkt.category.presentation.dto.RegisterCategoryRequest
import com.market.studyboardkt.setting.common.controller.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(val categoryService: CategoryService) {

    @GetMapping("/get/category/list")
    @Operation(summary = "전체 카테고리 리스트 조회 API", description = "게시판 카테고리 리스트를 조회")
    fun getCategoryList(): BaseResponse<CategoryListResponseDto> {
        val result = categoryService.getCategoryList()
        return BaseResponse("OK",result)
    }

    @PostMapping("/register/category")
    @Operation(summary = "카테고리 등록 API", description = "신규 카테고리 등록")
    fun registerCategory(request: RegisterCategoryRequest): BaseResponse<String> {
        categoryService.registerCategory(request.toRegisterCategoryDto())
        return BaseResponse("OK", "카테고리 등록이 완료했습니다.")
    }
}