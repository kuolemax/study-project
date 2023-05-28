package com.kuolemax.studyprojectbackend.config

import com.kuolemax.studyprojectbackend.entity.DepartmentImpl
import com.kuolemax.studyprojectbackend.entity.Entity
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class KtormInterfaceArgumentResolver: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return Entity::class.java.isAssignableFrom(parameter.parameterType)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any {
        return DepartmentImpl().apply {
            id = webRequest.getParameter("id")?.toInt()
            name = webRequest.getParameter("name")
            location = webRequest.getParameter("location")
        }
    }
}