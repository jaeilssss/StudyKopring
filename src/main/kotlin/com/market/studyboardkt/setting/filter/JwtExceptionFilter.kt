package com.market.studyboardkt.setting.filter

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.JWTErrorEnum
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter

class JwtExceptionFilter : OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        response.characterEncoding = "utf-8"
        try {
            filterChain.doFilter(request, response)
        } catch (e: ErrorException) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e)
        }
    }
    private fun setErrorResponse(status: HttpStatus, res: HttpServletResponse, e: ErrorException) {
        res.status = status.value()
        res.contentType = "application/json; charset=UTF-8"
        res.writer.write(tokenExpiredResponseToJson(e.errorMessage))
    }

    private fun tokenExpiredResponseToJson(message: String): String {
        val gson = Gson()
        val jsonObject = JsonObject()
        jsonObject.addProperty("code", "Token-Error")
        jsonObject.addProperty("message", message)
        return gson.toJson(jsonObject)
    }
}