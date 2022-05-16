package com.algolia.search.model.response.revision

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RevisionABTest(
    /**
     * Generated [ABTestID] of the [ABTest].
     */
    @SerialName(Key.ABTestID) val abTestID: ABTestID,
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(Key.TaskID) override val taskID: TaskID,
    /**
     * Base [IndexName] for the [ABTest].
     */
    @SerialName(Key.Index) val indexName: IndexName
) : Task
