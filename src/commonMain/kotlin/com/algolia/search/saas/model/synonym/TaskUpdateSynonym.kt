package com.algolia.search.saas.model.synonym

import com.algolia.search.saas.model.ObjectID
import com.algolia.search.saas.model.common.Task
import com.algolia.search.saas.model.common.TaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateSynonym(
    val updatedAt: String,
    @SerialName("id") val objectID: ObjectID,
    override val taskID: TaskID
) : Task