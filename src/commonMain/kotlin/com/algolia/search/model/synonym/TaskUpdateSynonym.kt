package com.algolia.search.model.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateSynonym(
    val updatedAt: String,
    @SerialName("id") val objectID: ObjectID,
    override val taskID: TaskID
) : Task