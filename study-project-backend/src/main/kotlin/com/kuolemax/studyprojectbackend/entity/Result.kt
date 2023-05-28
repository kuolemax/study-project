package com.kuolemax.studyprojectbackend.entity

data class Result<T>(
    var code: Int,
    var success: Boolean,
    var msg: String,
    var data: T?
) {
    constructor(status: ResultStatus, data: T? = null) : this(
        code = status.code,
        success = true,
        msg = status.msg,
        data
    )

    constructor(status: ResultStatus, msg: String?) : this(
        code = status.code,
        success = false,
        msg = msg ?: status.msg,
        data = null
    )
}

fun <T> success(data: T? = null): Result<T> {
    return Result(ResultStatus.SUCCESS, data)
}

fun <T> failure(status: ResultStatus = ResultStatus.FAILURE, msg: String?): Result<T> {
    return Result(status, msg)
}

enum class ResultStatus(val code: Int, val msg: String) {
    SUCCESS(200, "操作成功"),
    FAILURE(500, "操作失败"),
    UNAUTHORIZED(401, "无权访问")
}