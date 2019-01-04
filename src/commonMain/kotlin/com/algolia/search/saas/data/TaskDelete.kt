package com.algolia.search.saas.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    val deletedAt: String,
    @SerialName("taskID") override val taskId: Long
) : TaskId