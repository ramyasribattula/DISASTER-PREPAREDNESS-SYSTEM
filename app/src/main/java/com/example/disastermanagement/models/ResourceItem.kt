package com.example.disastermanagement.models

data class ResourceItem(
    val id: String,
    val title: String,
    val description: String,
    val type: ResourceType,
    val size: String,
    val downloadUrl: String,
    val isDownloaded: Boolean = false
)

enum class ResourceType {
    PDF, DOCUMENT, IMAGE, VIDEO, AUDIO, OTHER
}

