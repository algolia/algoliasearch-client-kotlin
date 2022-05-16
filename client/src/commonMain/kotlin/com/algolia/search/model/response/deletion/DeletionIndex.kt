package com.algolia.search.model.response.deletion

import com.algolia.search.client.Index
import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.ClientDate
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class DeletionIndex(
    /**
     * Date at which the [Task] to delete the [Index] has been created.
     */
    @SerialName(Key.DeletedAt) val deletedAt: ClientDate,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(Key.TaskID) override val taskID: TaskID
) : Task
