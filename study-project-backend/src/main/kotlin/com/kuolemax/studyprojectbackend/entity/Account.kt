package com.kuolemax.studyprojectbackend.entity

import com.easy.query.core.annotation.Column
import com.easy.query.core.annotation.Table

@Table(value = "account", schema = "user_center")
data class Account(
    @JvmField
    @Column(primaryKey = true, increment = true)
    var id: Long,
    @JvmField
    @Column(value = "name")
    var name: String,
    @JvmField
    @Column(value = "email")
    var email: String,
    @JvmField
    @Column(value = "password")
    var password: String
)