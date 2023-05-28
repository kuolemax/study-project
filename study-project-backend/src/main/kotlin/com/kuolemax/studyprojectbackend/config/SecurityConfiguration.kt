package com.kuolemax.studyprojectbackend.config

import com.alibaba.fastjson2.toJSONString
import com.kuolemax.studyprojectbackend.entity.ResultStatus
import com.kuolemax.studyprojectbackend.entity.failure
import com.kuolemax.studyprojectbackend.entity.success
import com.kuolemax.studyprojectbackend.service.AuthorizeService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

/**
 * SpringSecurity配置
 * @author kuolemax
 * @date 2023/05/28
 * @constructor 创建[SecurityConfiguration]
 */
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Autowired
    lateinit var authorizeService: AuthorizeService

    /**
     * SecurityFilterChain
     * @param [http] http
     * @return [SecurityFilterChain]
     */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http {
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }

            formLogin {
                loginProcessingUrl = "/auth/login"
                authenticationSuccessHandler = AuthenticationSuccessHandler(::onAuthenticationSuccess)
                authenticationFailureHandler = AuthenticationFailureHandler(::onAuthenticationFailure)
            }

            logout {
                logoutUrl = "/auth/logout"
            }

            csrf { disable() }

            exceptionHandling {
                authenticationEntryPoint = AuthenticationEntryPoint(::onAuthenticationFailure)
            }
        }
        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java).also {
            it.userDetailsService(authorizeService)
        }.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onAuthenticationSuccess(
        request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication
    ) {
        response.characterEncoding = "UTF-8"
        response.writer.write(success<String>().toJSONString())
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onAuthenticationFailure(
        request: HttpServletRequest, response: HttpServletResponse, err: AuthenticationException
    ) {
        response.characterEncoding = "UTF-8"
        response.writer.write(failure<String>(ResultStatus.UNAUTHORIZED, err.message).toJSONString())
    }

}


