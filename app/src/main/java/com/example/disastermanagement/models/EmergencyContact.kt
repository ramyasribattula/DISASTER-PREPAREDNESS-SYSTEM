package com.example.disastermanagement.models

data class EmergencyContact(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val organization: String,
    val role: String,
    val isPrimary: Boolean = false,
    val category: ContactCategory
)

enum class ContactCategory {
    POLICE, FIRE_DEPARTMENT, MEDICAL, DISASTER_MANAGEMENT, SCHOOL_ADMIN, PARENT, OTHER
}

