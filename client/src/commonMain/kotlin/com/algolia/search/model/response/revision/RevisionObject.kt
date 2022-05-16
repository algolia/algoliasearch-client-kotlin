package com.algolia.search.model.response.revision

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.ClientDate
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RevisionObject(
    /**
     * The date at which the record has been revised.
     */
    @SerialName(Key.UpdatedAt) val updatedAt: ClientDate,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(Key.TaskID) override val taskID: TaskID,
    /**
     * The inserted record [ObjectID]
     */
    @SerialName(Key.ObjectID) val objectID: ObjectID
) : Task
