package com.market.studyboardkt.setting.common.exception

import org.springframework.http.HttpStatus

class ErrorException(
    val httpStatus: HttpStatus,
    val errorMessage : String
) : RuntimeException(){

}