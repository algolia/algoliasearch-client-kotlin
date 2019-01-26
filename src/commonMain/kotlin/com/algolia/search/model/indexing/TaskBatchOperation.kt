package com.algolia.search.model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import kotlinx.serialization.Serializable


@Serializable
data class TaskBatchOperation(
    override val taskID: TaskID,
    val objectIDs: List<ObjectID?>
) : Task