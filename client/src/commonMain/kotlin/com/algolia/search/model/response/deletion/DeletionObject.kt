package com.algolia.search.model.response.deletion

import com.algolia.search.model.ClientDate
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DeletionObject(
    @SerialName(Key.DeletedAt) val deletedAt: ClientDate,
    @SerialName(Key.TaskID) override val taskID: TaskID,
    @SerialName(Key.ObjectID) val objectID: ObjectID
) : Task
