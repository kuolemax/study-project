package com.kuolemax.studyprojectbackend.service

import com.kuolemax.studyprojectbackend.mapper.AccountMapper
import com.kuolemax.studyprojectbackend.util.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorizeService : UserDetailsService {

    @Autowired
    lateinit var accountMapper: AccountMapper

    override fun loadUserByUsername(username: String?): UserDetails {
        assertNotNull(username, UsernameNotFoundException::class, msg = "用户名必填")
        val account = accountMapper.findAccountByEmailOrName(username!!)
        assertNotNull(account, UsernameNotFoundException::class, msg = "用户名或密码错误")
        return User.withUsername(account!!.name)
            .password(account.password)
            .roles("user")
            .build()
    }
}