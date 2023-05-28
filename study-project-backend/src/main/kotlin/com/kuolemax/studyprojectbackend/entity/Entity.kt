package com.kuolemax.studyprojectbackend.entity

interface Entity<T> {
    fun create(): T
}