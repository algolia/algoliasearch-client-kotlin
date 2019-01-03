package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskUpdate(
    val updatedAt: String,
    override val taskID: Long
) : TaskId