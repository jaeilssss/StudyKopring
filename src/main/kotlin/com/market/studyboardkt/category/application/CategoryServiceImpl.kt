package com.market.studyboardkt.category.application

import com.market.studyboardkt.category.application.dto.request.RegisterCategoryDto
import com.market.studyboardkt.category.application.dto.response.CategoryListResponseDto
import com.market.studyboardkt.category.domain.entity.Category
import com.market.studyboardkt.category.domain.repository.CategoryRepository
import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.CategoryErrorEnum
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryServiceImpl(private val repository: CategoryRepository) : CategoryService {
    override fun getCategoryList(): CategoryListResponseDto {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun registerCategory(request: RegisterCategoryDto) {
        val parentCategory = request.parentCategory?.let { getCategory(it) }

        repository.save(request.toEntity(parentCategory))
    }

    private fun getCategory(id: Long): Category = repository.findById(id).orElseThrow {
        ErrorException(
            CategoryErrorEnum.NOT_FOUND_PARENT_CATEGORY.httpStatus,
            CategoryErrorEnum.NOT_FOUND_PARENT_CATEGORY.message
        )
    }
}