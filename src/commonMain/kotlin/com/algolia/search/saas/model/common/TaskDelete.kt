package com.algolia.search.saas.model.common

import com.algolia.search.saas.serialize.KeyDeletedAt
import com.algolia.search.saas.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    @SerialName(KeyDeletedAt) override val date: String,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task, Datable