package com.market.studyboardkt.setting.common.controller

import com.market.studyboardkt.setting.common.exception.ErrorException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BaseController {

    @ExceptionHandler(ErrorException::class)
    fun exceptionHandler(e: ErrorException): ResponseEntity<BaseResponse<String>> {
        val response = BaseResponse<String>("error", e.errorMessage)
        return ResponseEntity(response, e.httpStatus)
    }
}