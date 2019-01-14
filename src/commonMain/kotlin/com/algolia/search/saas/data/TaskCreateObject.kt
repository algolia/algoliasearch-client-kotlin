package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskCreateObject(
    val createdAt: String,
    val objectID: ObjectID,
    override val taskID: TaskID
) : Task