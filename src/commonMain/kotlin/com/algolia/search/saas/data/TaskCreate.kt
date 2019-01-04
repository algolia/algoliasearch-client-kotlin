package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskCreate(
    val createdAt: String,
    val objectID: ObjectId,
    override val taskID: TaskId
) : Task