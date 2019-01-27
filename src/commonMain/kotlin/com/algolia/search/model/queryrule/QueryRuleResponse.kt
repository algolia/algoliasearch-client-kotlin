package com.algolia.search.model.queryrule

import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class QueryRuleResponse {

    @Serializable
    data class Update(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable


    @Serializable
    data class Search(
        @SerialName(KeyHits) val hits: List<QueryRule>,
        @SerialName(KeyNbHits) val nbHits: Int
    )
}