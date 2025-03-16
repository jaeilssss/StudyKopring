package com.market.studyboardkt.setting.common.controller

class BaseResponse<T>(
    val message : String,
    val data: T
){
}