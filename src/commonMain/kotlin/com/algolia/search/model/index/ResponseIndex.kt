package com.algolia.search.model.index

import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.KeyDeletedAt
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class ResponseIndex {

    @Serializable
    data class Update(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable

    @Serializable
    data class Delete(
        @SerialName(KeyDeletedAt) override val date: String,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable
}