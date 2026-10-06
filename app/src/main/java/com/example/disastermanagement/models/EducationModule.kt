package com.example.disastermanagement.models

import java.io.Serializable

data class EducationModule(
    val id: String,
    val title: String,
    val description: String,
    val disasterType: DisasterType,
    val content: String,
    val quiz: List<QuizQuestion>,
    val duration: Int, // in minutes
    val difficulty: DifficultyLevel,
    val regionSpecific: Boolean = false,
    val region: String? = null
) : Serializable

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String
) : Serializable

enum class DifficultyLevel : Serializable {
    BEGINNER, INTERMEDIATE, ADVANCED
}

