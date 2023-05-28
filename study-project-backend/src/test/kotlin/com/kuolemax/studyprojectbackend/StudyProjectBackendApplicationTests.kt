package com.kuolemax.studyprojectbackend

import com.kuolemax.studyprojectbackend.entity.Department
import com.kuolemax.studyprojectbackend.entity.Entity
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

// @SpringBootTest
class StudyProjectBackendApplicationTests {

    @Test
    fun contextLoads() {
        val encoder = BCryptPasswordEncoder()
        val password = "123456"
        println(encoder.encode(password))
    }

    @Test
    fun testGetPropertyName() {
        // println(EasyLambdaUtil.getPropertyName(Account::email))
        println(Department::class.java.isAssignableFrom(Entity::class.java))
        println(Entity::class.java.isAssignableFrom(Department::class.java))
    }

}
