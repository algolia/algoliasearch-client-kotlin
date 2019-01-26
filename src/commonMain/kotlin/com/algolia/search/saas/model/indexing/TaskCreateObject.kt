package com.algolia.search.saas.model.indexing

import com.algolia.search.saas.model.ObjectID
import com.algolia.search.saas.model.Task
import com.algolia.search.saas.model.TaskID
import kotlinx.serialization.Serializable


@Serializable
data class TaskCreateObject(
    val createdAt: String,
    val objectID: ObjectID,
    override val taskID: TaskID
) : Task