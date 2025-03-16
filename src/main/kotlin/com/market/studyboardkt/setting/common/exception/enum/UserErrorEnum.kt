package com.market.studyboardkt.setting.common.exception.enum

import org.springframework.http.HttpStatus

enum class UserErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {
    IS_EXISTED_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다."),
    NOT_FOUND_USER_INFO(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다.")
}