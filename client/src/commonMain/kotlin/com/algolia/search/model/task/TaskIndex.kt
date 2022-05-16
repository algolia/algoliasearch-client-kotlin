package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TaskIndex(
    /**
     * The [IndexName] this task is running on.
     */
    @SerialName(Key.IndexName) val indexName: IndexName,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(Key.TaskID) override val taskID: TaskID
) : Task
