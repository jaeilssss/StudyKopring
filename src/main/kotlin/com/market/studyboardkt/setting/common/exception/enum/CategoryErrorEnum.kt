package com.market.studyboardkt.setting.common.exception.enum

import org.springframework.http.HttpStatus

enum class CategoryErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND_PARENT_CATEGORY(HttpStatus.NOT_FOUND, "부모 카테고리 Id는 카테고리를 찾을 수 없습니다")
}