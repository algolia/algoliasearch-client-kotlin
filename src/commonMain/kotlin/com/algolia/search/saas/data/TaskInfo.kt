package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    val status: TaskStatus
)