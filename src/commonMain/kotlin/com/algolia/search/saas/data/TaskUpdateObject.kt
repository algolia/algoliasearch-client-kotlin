package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateObject(
    val updatedAt: String,
    val objectID: ObjectId,
    override val taskID: TaskId
) : Task