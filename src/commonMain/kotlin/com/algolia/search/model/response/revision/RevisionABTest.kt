package com.algolia.search.model.response.revision

import com.algolia.search.model.IndexName
import com.algolia.search.model.analytics.ABTestID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class RevisionABTest(
    @SerialName(KeyABTestID) val abTestID: ABTestID,
    @SerialName(KeyTaskID) override val taskID: TaskID,
    @SerialName(KeyIndex) val indexName: IndexName
) : Task