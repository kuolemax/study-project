package com.kuolemax.studyprojectbackend.entity

interface Department : Entity<Department> {
    var id: Int?
    var name: String?
    var location: String?
}


data class DepartmentImpl(
    override var id: Int? = null,
    override var name: String? = null,
    override var location: String? = null
) : Department {

    override fun create(): Department {
        return DepartmentImpl()
    }
}
