package com.algolia.search.saas.model.common

import kotlinx.serialization.Serializable


@Serializable
data class TaskInfo(
    val status: TaskStatus
)