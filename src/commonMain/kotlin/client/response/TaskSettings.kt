package client.response

import kotlinx.serialization.Serializable


@Serializable
data class TaskSettings(
    val updatedAt: String,
    override val taskID: Long
) : TaskId