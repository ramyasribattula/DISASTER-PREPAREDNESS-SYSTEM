package com.example.disastermanagement.models

import java.io.Serializable
import java.util.Date

data class Drill(
    val id: String,
    val name: String,
    val disasterType: DisasterType,
    val description: String,
    val steps: List<DrillStep>,
    val duration: Int, // in minutes
    val difficulty: DifficultyLevel,
    val isVirtual: Boolean = true,
    val scheduledDate: Date? = null,
    val isCompleted: Boolean = false,
    val score: Int? = null,
    // Reference video links (e.g., YouTube) for drill guidance
    val referenceVideos: List<String> = emptyList()
) : Serializable

data class DrillStep(
    val stepNumber: Int,
    val instruction: String,
    val action: String,
    val timeLimit: Int, // in seconds
    val isCompleted: Boolean = false
) : Serializable

data class DrillResult(
    val drillId: String,
    val userId: String,
    val score: Int,
    val totalSteps: Int,
    val completedSteps: Int,
    val timeTaken: Long, // in milliseconds
    val dateCompleted: Date
) : Serializable

