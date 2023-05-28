package com.kuolemax.studyprojectbackend.mapper

import com.easy.query.core.api.client.EasyQuery
import com.kuolemax.studyprojectbackend.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountMapper {

    @Autowired
    lateinit var query: EasyQuery

    fun findAccountByEmailOrName(emailOrName: String): Account? {
        return query.queryable(Account::class.java)
            .where {
                it.eq(Account::email, emailOrName)
                    .or()
                    .eq(Account::name, emailOrName)
            }
            .firstOrNull()
    }
}