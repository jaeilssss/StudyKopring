package com.market.studyboardkt.setting.common.exception.enum

import org.springframework.http.HttpStatus


enum class JWTErrorEnum(
    val httpStatus: HttpStatus,
    val message: String
) {
    INVALID_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "잘못된 토큰 입니다"),
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다"),
    UNSUPPORTED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "지원하지 않은 토큰 입니다."),
    ILLEGAL_JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "토큰이 정상적이지 않습니다."),
    NOT_CONTAIN_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레쉬 토큰이 존재하지 않습니다.")

}