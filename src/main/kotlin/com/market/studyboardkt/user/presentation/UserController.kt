package com.market.studyboardkt.user.presentation

import com.market.studyboardkt.setting.common.controller.BaseResponse
import com.market.studyboardkt.user.application.UserService
import com.market.studyboardkt.user.application.dto.request.LoginDto
import com.market.studyboardkt.user.application.dto.response.LoginResponseDto
import com.market.studyboardkt.user.presentation.dto.LoginRequest
import com.market.studyboardkt.user.presentation.dto.SignupRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Tag(name = "User")
class UserController(val userService: UserService) {

    @PostMapping("/signup")
    @Operation(summary = "회원가입 API", description = "일반 유저가 회원가입 하는 API")
    fun signUp(@RequestBody signUpRequest: SignupRequest): BaseResponse<String> {
        userService.signUp(signUpRequest.toSignUpDto())
        return BaseResponse<String>("OK", "회원가입이 성공적으로 완료했습니다.")
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 API", description ="로그인 API")
    fun login(@RequestBody loginRequest: LoginRequest) : BaseResponse<LoginResponseDto> {
        val result = userService.login(LoginDto(loginRequest.email, loginRequest.password))
        return BaseResponse("OK", result)
    }
}