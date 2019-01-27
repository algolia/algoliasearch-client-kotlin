package com.algolia.search.response.revision

import com.algolia.search.model.Datable
import com.algolia.search.model.Waitable
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RevisionIndex(
    @SerialName(KeyUpdatedAt) override val date: String,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Waitable, Datable