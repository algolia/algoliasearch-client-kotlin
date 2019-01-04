package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    val deletedAt: String,
    override val taskID: TaskId
) : Task