package com.algolia.search.model.response.deletion

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class DeletionABTest(
    /**
     * Generated [ABTestID] of the [ABTest].
     */
    @SerialName(KeyABTestID) val abTestID: ABTestID,
    /**
     * The [TaskID] used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(KeyTaskID) override val taskID: TaskID,
    /**
     * Base [IndexName] for the [ABTest].
     */
    @SerialName(KeyIndex) val indexName: IndexName
) : Task