package com.market.studyboardkt.user.presentation

import com.market.studyboardkt.setting.common.annotation.DisableSwaggerSecurity
import com.market.studyboardkt.setting.common.controller.BaseResponse
import com.market.studyboardkt.setting.common.exception.ErrorException
import com.market.studyboardkt.setting.common.exception.enum.JWTErrorEnum
import com.market.studyboardkt.user.application.UserService
import com.market.studyboardkt.user.application.dto.request.LoginDto
import com.market.studyboardkt.user.application.dto.response.LoginResponseDto
import com.market.studyboardkt.user.application.dto.response.UserInfoResponseDto
import com.market.studyboardkt.user.domain.dto.request.JwtProperties
import com.market.studyboardkt.user.presentation.dto.LoginRequest
import com.market.studyboardkt.user.presentation.dto.SignupRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpRequest
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User")
class UserController(
    val userService: UserService,
    private val jwtProperties: JwtProperties,
) {

    @PostMapping("/signup")
    @DisableSwaggerSecurity
    @Operation(summary = "회원가입 API", description = "일반 유저가 회원가입 하는 API", security = [])
    fun signUp(@RequestBody signUpRequest: SignupRequest): BaseResponse<String> {
        userService.signUp(signUpRequest.toSignUpDto())
        return BaseResponse<String>("OK", "회원가입이 성공적으로 완료했습니다.")
    }

    @PostMapping("/login")
    @DisableSwaggerSecurity
    @Operation(summary = "로그인 API", description ="로그인 API")
    fun login(httpResponse: HttpServletResponse, @RequestBody loginRequest: LoginRequest) : BaseResponse<LoginResponseDto> {
        val result = userService.login(LoginDto(loginRequest.email, loginRequest.password))
        val cookie = Cookie("refreshToken", result.refreshToken).apply {
            isHttpOnly = true
            secure = true
            path = "/"
            maxAge = jwtProperties.refreshExpiration.toInt()
        }
        httpResponse.addCookie(cookie)
        return BaseResponse("OK", result)
    }

    @GetMapping("/myInfo/{userId}")
    @Operation(summary = "내 정보 조회하기 API", description = "내 정보 조회하기")
    fun getUserInfo(@PathVariable userId: Long) : BaseResponse<UserInfoResponseDto> {
        val result = userService.getUserInfo(userId)
        return BaseResponse("Ok", result)
    }

    @PostMapping("/refresh/renew")
    @DisableSwaggerSecurity
    @Operation(summary = "토큰 재발급 API", description = "refresh Token 을 이용한 토큰 재발급")
    fun renewJwtToken(request: HttpServletRequest) : BaseResponse<LoginResponseDto> {
        val refreshToken = extractRefreshTokenFromCookies(request)
            ?: throw ErrorException(
                JWTErrorEnum.NOT_CONTAIN_REFRESH_TOKEN.httpStatus,
                JWTErrorEnum.NOT_CONTAIN_REFRESH_TOKEN.message
            )
        val result = userService.renewJwtToken(refreshToken)

        return BaseResponse("Ok", result)
    }

    private fun extractRefreshTokenFromCookies(request: HttpServletRequest) : String? {
        return request.cookies?.firstOrNull { it.name == "refreshToken" }?.value
    }
}