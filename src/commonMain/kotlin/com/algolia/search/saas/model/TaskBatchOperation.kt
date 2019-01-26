package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TaskBatchOperation(
    override val taskID: TaskID,
    val objectIDs: List<ObjectID?>
) : Task