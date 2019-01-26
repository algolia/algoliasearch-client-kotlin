package com.algolia.search.model.common

import com.algolia.search.serialize.KeyDeletedAt
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    @SerialName(KeyDeletedAt) override val date: String,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task, Datable