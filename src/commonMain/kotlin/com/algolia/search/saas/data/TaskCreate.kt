package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskCreate(
    val createdAt: String,
    override val taskID: TaskID
) : Task