package client.response

import kotlinx.serialization.Serializable


@Serializable
data class Task(
    val updatedAt: String,
    val taskID: Long
)