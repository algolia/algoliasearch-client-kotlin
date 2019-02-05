package com.algolia.search.model.response.deletion

import com.algolia.search.model.Datable
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyDeletedAt
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeletionObject(
    @SerialName(KeyDeletedAt) override val date: String,
    @SerialName(KeyTaskID) override val taskID: TaskID,
    @SerialName(KeyObjectID) val objectID: ObjectID
) : Task, Datable