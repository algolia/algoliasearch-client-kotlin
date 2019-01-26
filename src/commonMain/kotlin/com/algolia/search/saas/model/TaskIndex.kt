package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TaskIndex(
    val indexName: IndexName,
    override val taskID: TaskID
) : Task