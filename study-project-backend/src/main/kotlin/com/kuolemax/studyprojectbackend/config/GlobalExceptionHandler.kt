package com.kuolemax.studyprojectbackend.config

import com.kuolemax.studyprojectbackend.entity.Result
import com.kuolemax.studyprojectbackend.entity.failure
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun fallbackExceptionHandler(err: Exception): Result<String> {
        return failure(msg = err.message)
    }

}