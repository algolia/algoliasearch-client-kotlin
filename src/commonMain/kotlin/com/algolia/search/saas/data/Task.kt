package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Task(
    val updatedAt: String,
    override val taskID: Long
) : TaskId