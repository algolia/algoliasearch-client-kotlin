package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TaskDelete(
    val deletedAt: String,
    override val taskID: TaskID
) : Task