package com.algolia.search.model.common

import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    val status: TaskStatus
)