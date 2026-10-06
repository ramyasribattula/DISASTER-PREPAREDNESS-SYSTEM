package com.example.disastermanagement.models

import java.io.Serializable

enum class UserCategoryType : Serializable {
    STUDENTS,
    TEACHERS_STAFF,
    INSTITUTIONS_RESPONSE_TEAMS,
    PARENTS_GUARDIANS,
    GOVERNMENT_DEPARTMENTS
}

data class UserCategory(
    val type: UserCategoryType,
    val title: String,
    val description: String,
    val iconRes: Int
) : Serializable


