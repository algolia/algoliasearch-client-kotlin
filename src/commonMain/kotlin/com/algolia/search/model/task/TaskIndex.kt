package com.algolia.search.model.task

import com.algolia.search.model.IndexName
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class TaskIndex(
    @SerialName(KeyIndexName) val indexName: IndexName,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task