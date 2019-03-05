package com.algolia.search.model.response.deletion

import com.algolia.search.ClientDate
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyDeletedAt
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeletionIndex(
    @SerialName(KeyDeletedAt) val deletedAt: ClientDate,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task