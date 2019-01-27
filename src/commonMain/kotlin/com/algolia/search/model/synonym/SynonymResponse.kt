package com.algolia.search.model.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class SynonymResponse {

    @Serializable
    data class UpdateObject(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyId) val objectID: ObjectID,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable

    @Serializable
    data class Update(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable

    @Serializable
    data class Delete(
        @SerialName(KeyDeletedAt) override val date: String,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable

    @Serializable
    data class Search(
        @SerialName(KeyHits) val hits: List<Synonym>,
        @SerialName(KeyNbHits) val nbHits: Int
    )
}