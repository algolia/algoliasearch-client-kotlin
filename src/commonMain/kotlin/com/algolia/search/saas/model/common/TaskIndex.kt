package com.algolia.search.saas.model.common

import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.serialize.KeyIndexName
import com.algolia.search.saas.serialize.KeyTaskId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TaskIndex(
    @SerialName(KeyIndexName) val indexName: IndexName,
    @SerialName(KeyTaskId) override val taskID: TaskID
) : Task