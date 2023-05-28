package com.kuolemax.studyprojectbackend.util

import kotlin.reflect.KClass

fun assertNotNull(obj: Any?, errType: KClass<out java.lang.RuntimeException> = RuntimeException::class, msg: String = "参数异常") {
    when {
        obj == null
            || obj is Array<*> && obj.isEmpty()
            || obj is Collection<*> && obj.isEmpty()
            || obj is String && obj.isEmpty()
        -> {
            throw errType.constructors.first().call(msg)
        }
    }
}