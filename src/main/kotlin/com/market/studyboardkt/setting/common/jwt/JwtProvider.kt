package com.market.studyboardkt.setting.common.jwt

import com.market.studyboardkt.user.domain.dto.request.JwtProperties
import com.market.studyboardkt.user.domain.dto.response.JwtTokenResponse
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProvider(val jwtProperties: JwtProperties) {

    fun createToken(userId: Long, email: String): JwtTokenResponse {
        val claims = Jwts.claims()

        claims[jwtProperties.headers] = email
        val now = Date().time

        val accessExpiration = Date(now + jwtProperties.expiration)
        val refreshExpiration = Date(now + jwtProperties.refreshExpiration)

        val accessToken = Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(accessExpiration)
            .setSubject(email)
            .claim("userId", userId)
            .signWith(SignatureAlgorithm.HS256, getKey(jwtProperties.secretKey))
            .compact()

        val refreshToken = Jwts.builder()
            .setExpiration(refreshExpiration)
            .signWith(SignatureAlgorithm.HS256, getKey(jwtProperties.secretKey))
            .compact()

        return JwtTokenResponse(accessToken, refreshToken)
    }

    private fun getKey(secretKey: String) : SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey.replace(" ", ""))
        return Keys.hmacShaKeyFor(keyBytes)
    }
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(jwtProperties.secretKey))
        }catch (e: Exception) {
            when(e) {
                is io.jsonwebtoken.security.SecurityException, is MalformedJwtException ->{
                    println("Invalid JWT Token")
                }
                is ExpiredJwtException ->{
                    println("Expired JWT token")
                    throw JwtException("error")
                }
                is UnsupportedJwtException -> {
                    println("UnsupportedJwtException")
                }
                is IllegalArgumentException -> {
                    println("JWT Claims string is empty")
                }
            }
        }
        return false
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = parse(accessToken)

        if(claims["userId"] == null) {
            throw RuntimeException("권한 정보가 없는 토큰입니다.")
        }

        val authorities: Collection<GrantedAuthority> = claims["userId"]
            .toString()
            .split(",")
            .map(::SimpleGrantedAuthority)

        return UsernamePasswordAuthenticationToken(claims["userId"], "", authorities)
    }

    fun parse(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getKey(jwtProperties.secretKey))
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }
}