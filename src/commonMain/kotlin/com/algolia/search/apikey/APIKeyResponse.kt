package com.algolia.search.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Datable
import com.algolia.search.model.common.Task
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


sealed class APIKeyResponse {

    @Serializable
    data class Get(
        @SerialName(KeyValue) val apiKey: APIKey,
        @SerialName(KeyValidity) val validity: Long,
        @SerialName(KeyAcl) val rights: List<ACL>,
        @Optional @SerialName(KeyCreatedAt) val createdAt: Long? = null,
        @Optional @SerialName(KeyDescription) val description: String? = null,
        @Optional @SerialName(KeyIndex) val index: IndexName? = null
    )

    @Serializable
    data class Delete(
        @SerialName(KeyDeletedAt) override val date: String
    ) : Datable

    @Serializable
    data class GetList(
        @SerialName(KeyKeys) val keys: List<Get>
    )

    @Serializable
    data class Save(
        @SerialName(KeyKey) val apiKey: APIKey,
        @SerialName(KeyCreatedAt) override val date: String
    ) : Datable

    @Serializable
    data class Update(
        @SerialName(KeyUpdatedAt) override val date: String,
        @SerialName(KeyObjectID) val objectID: ObjectID,
        @SerialName(KeyTaskID) override val taskID: TaskID
    ) : Task, Datable
}