package com.algolia.search.response.revision

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.KeyId
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RevisionObject(
    @SerialName(KeyUpdatedAt) override val date: String,
    @SerialName(KeyId) val objectID: ObjectID,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task, Datable