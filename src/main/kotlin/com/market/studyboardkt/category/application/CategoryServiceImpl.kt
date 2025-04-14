package com.market.studyboardkt.category.application

import com.market.studyboardkt.category.application.dto.request.ModifyCategoryDto
import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto
import com.market.studyboardkt.category.application.dto.response.AllCategoryListResponseDto
import com.market.studyboardkt.category.application.dto.response.CategoryResponseDto
import com.market.studyboardkt.category.application.dto.toCategoryResponseDto
import com.market.studyboardkt.category.domain.entity.Category
import com.market.studyboardkt.category.domain.repository.CategoryRepository
import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.CategoryErrorEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Service
@Transactional(readOnly = true)
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {

    override fun getCategoryList(): AllCategoryListResponseDto? {
        val categoryList = repository.findAll()
        val resultNode : MutableMap<Long, MutableList<CategoryResponseDto>> = mutableMapOf()
        val result : MutableList<CategoryResponseDto> = mutableListOf()
        categoryList?.stream()?.map {
            if(it.parent != null) {
                resultNode[it.parent!!.id]?.add(
                    
                )
            }
        }


    }



    @Transactional
    override fun registerCategory(request: RegisterCategoryDto) {
        val parentCategory = request.parentCategory?.let { getParentCategory(it) }
        repository.save(request.toEntity(parentCategory))
    }

    @Transactional
    override fun modifyCategory(request: ModifyCategoryDto) {
        val category = getParentCategory(request.categoryId)
        val parent = request.parentId?.let {
            getCategory(it)
        }
        category.modify(parent, request)
    }

    private fun getParentCategory(id: Long): Category = repository.findById(id).orElseThrow {
        ErrorException(
            CategoryErrorEnum.NOT_FOUND_PARENT_CATEGORY.httpStatus,
            CategoryErrorEnum.NOT_FOUND_PARENT_CATEGORY.message
        )
    }

    private fun getCategory(id: Long) : Category = repository.findById(id).orElseThrow {
        ErrorException(
            CategoryErrorEnum.NOT_FOUND_CATEGORY.httpStatus,
            CategoryErrorEnum.NOT_FOUND_CATEGORY.message
        )
    }

}