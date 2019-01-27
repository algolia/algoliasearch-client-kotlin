package com.algolia.search.model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class IndexingResponse {

    @Serializable
    data class CreateObject(
        @SerialName(KeyCreatedAt) override val date: String,
        @SerialName(KeyObjectID) val objectID: ObjectID,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable

    @Serializable
    data class UpdateObject(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyObjectID) val objectID: ObjectID,
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
    data class Batch(
        @SerialName(KeyTaskID) override val taskID: TaskID,
        @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID?>
    ) : Task
}