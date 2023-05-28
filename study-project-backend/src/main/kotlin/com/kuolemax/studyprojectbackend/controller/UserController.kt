package com.kuolemax.studyprojectbackend.controller

import com.kuolemax.studyprojectbackend.entity.Department
import com.kuolemax.studyprojectbackend.entity.Result
import com.kuolemax.studyprojectbackend.entity.success
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/test")
    fun test(): Result<String> {
        return success("测试成功")
    }

    @GetMapping("/test2")
    fun test1(dept: Department): String {
        println(dept)
        return "测试1"
    }

    @GetMapping("/test3")
    fun test2(): Map<String, String> {
        return mapOf("a" to "c")
    }

}