package com.algolia.search.model.task

import com.algolia.search.serialize.KeyPendingTask
import com.algolia.search.serialize.KeyStatus
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    @SerialName(KeyStatus) val status: TaskStatus,
    @SerialName(KeyPendingTask) val pendingTask: Boolean
)