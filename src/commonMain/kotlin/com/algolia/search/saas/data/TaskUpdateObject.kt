package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateObject(
    val updatedAt: String,
    val objectID: ObjectID,
    override val taskID: TaskID
) : Task