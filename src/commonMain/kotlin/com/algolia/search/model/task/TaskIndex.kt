package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TaskIndex(
    /**
     * The [IndexName] this task is running on.
     */
    @SerialName(KeyIndexName) val indexName: IndexName,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task
