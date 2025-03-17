package com.market.studyboardkt.setting.filter

import com.market.studyboardkt.setting.common.jwt.JwtProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.GenericFilter
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils

class JwtAuthenticationFilter(val jwtProvider: JwtProvider) : GenericFilter() {
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, filterChain: FilterChain?) {
        val token: String? = (servletRequest as? HttpServletRequest)?.let { resolveToken(it) }

        if (token != null && jwtProvider.validateToken(token)) {
            val authentication = jwtProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain?.doFilter(servletRequest, servletResponse)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearToken) && bearToken.startsWith("Bearer")) {
            return bearToken.substring(7)
        }
        return null
    }
}