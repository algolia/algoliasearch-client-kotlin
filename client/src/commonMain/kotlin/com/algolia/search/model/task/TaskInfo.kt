package com.algolia.search.model.task

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TaskInfo(
    /**
     * The [Task] current [TaskStatus].
     */
    @SerialName(Key.Status) val status: TaskStatus,
    /**
     * Whether the index has remaining [Task]s running
     */
    @SerialName(Key.PendingTask) val pendingTask: Boolean
)
