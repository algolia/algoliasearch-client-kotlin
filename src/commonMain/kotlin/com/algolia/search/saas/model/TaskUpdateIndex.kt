package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdateIndex(
    val updatedAt: String,
    override val taskID: TaskID
) : Task