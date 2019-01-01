package client.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    val deletedAt: String,
    override val taskID: Long
) : TaskId