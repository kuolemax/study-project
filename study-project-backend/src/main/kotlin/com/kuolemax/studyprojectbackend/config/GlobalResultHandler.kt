package com.kuolemax.studyprojectbackend.config

import com.alibaba.fastjson2.toJSONString
import com.kuolemax.studyprojectbackend.entity.Result
import com.kuolemax.studyprojectbackend.entity.success
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class GlobalResultHandler : ResponseBodyAdvice<Any>{
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return !returnType.javaClass.isAssignableFrom(Result::class.java)
    }

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        return when (body) {
            is Result<*> -> body
            is String -> {
                // response.headers.contentType = MediaType.APPLICATION_JSON
                return success(body).toJSONString()
            }
            else -> success(body)
        }
    }
}