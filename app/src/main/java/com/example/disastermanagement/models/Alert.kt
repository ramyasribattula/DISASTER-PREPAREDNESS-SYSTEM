package com.example.disastermanagement.models

import java.util.Date

data class Alert(
    val id: String,
    val title: String,
    val message: String,
    val disasterType: DisasterType,
    val severity: AlertSeverity,
    val timestamp: Date,
    val isRead: Boolean = false,
    val actionRequired: Boolean = false,
    val actionText: String? = null
)

enum class AlertSeverity {
    LOW, MEDIUM, HIGH, CRITICAL
}

