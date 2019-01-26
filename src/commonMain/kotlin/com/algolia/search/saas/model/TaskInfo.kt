package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    val status: TaskStatus
)