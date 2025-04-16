package com.market.studyboardkt.category.presentation

import com.market.studyboardkt.category.application.CategoryService
import com.market.studyboardkt.category.application.dto.response.AllCategoryListResponseDto
import com.market.studyboardkt.category.application.dto.response.CategoryResponseDto
import com.market.studyboardkt.category.presentation.dto.ModifyCategoryRequest
import com.market.studyboardkt.category.presentation.dto.RegisterCategoryRequest
import com.market.studyboardkt.setting.common.controller.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
class CategoryController(val categoryService: CategoryService) {

    @GetMapping("/get/category/list")
    @Operation(summary = "전체 카테고리 리스트 조회 API", description = "게시판 카테고리 리스트를 조회")
    fun getCategoryList(): BaseResponse<AllCategoryListResponseDto?> {
        val result = categoryService.getCategoryList()
        return BaseResponse("OK",result)
    }

    @PostMapping("/register/category")
    @Operation(summary = "카테고리 등록 API", description = "신규 카테고리 등록")
    fun registerCategory(@RequestBody request: RegisterCategoryRequest): BaseResponse<String> {
        categoryService.registerCategory(request.toRegisterCategoryDto())
        return BaseResponse("OK", "카테고리 등록이 완료했습니다.")
    }

    @PutMapping("/modify/category")
    @Operation(summary = "카테고리 수정 API")
    fun modifyCategory(request: ModifyCategoryRequest): BaseResponse<String> {
        categoryService.modifyCategory(request.toDto())
        return BaseResponse("OK", "해당 카테고리 수정을 완료 했습니다.")
    }

    @DeleteMapping("/delete/category/{categoryId}")
    @Operation(summary = "카테고리 삭제 API")
    fun deleteCategory(@PathVariable categoryId: Long): BaseResponse<String> {
        categoryService.deleteCategory(categoryId)
        return BaseResponse("ok", "카테고리가 삭제됐습니다.")
    }
}