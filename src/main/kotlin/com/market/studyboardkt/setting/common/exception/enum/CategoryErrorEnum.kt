package com.market.studyboardkt.setting.common.exception.enum

import org.springframework.http.HttpStatus

enum class CategoryErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {
    NOT_FOUND_PARENT_CATEGORY(HttpStatus.NOT_FOUND, "부모 카테고리 Id는 카테고리를 찾을 수 없습니다"),
    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "해당 카테고리 ID는 찾을수없습니다.")

}