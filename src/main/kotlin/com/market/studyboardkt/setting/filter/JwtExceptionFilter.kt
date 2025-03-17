package com.market.studyboardkt.setting.filter

import com.google.gson.Gson
import com.google.gson.JsonObject
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
        } catch (e: JwtException) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e)
        }
    }

    fun setErrorResponse(status: HttpStatus, res: HttpServletResponse, ex: Throwable) {
        res.status = status.value()
        res.contentType = "application/json; charset=UTF-8"
        res.writer.write(tokenExpiredResponseToJson())
    }

    private fun tokenExpiredResponseToJson(): String {
        val gson = Gson()
        val jsonObject = JsonObject()
        jsonObject.addProperty("code", "Token-Expired")
        jsonObject.addProperty("message", "토큰이 만료 되었습니다")
        return gson.toJson(jsonObject)
    }
}