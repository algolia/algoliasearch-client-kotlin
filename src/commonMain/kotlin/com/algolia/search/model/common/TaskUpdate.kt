package com.algolia.search.model.common

import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdate(
    @SerialName(KeyUpdatedAt) override val date: String,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task, Datable