package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskBatchOperation(
    override val taskID: TaskID,
    val objectIDs: List<ObjectID?>
) : Task