package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateIndex(
    val updatedAt: String,
    override val taskID: TaskId
) : Task