package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject


@Serializable
data class ResponseListIndexes(
    @SerialName(KeyItems) val items: List<Item>,
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable
    data class Item(
        @SerialName(KeyName) val indexName: IndexName,
        @SerialName(KeyCreatedAt) val createdAt: String,
        @SerialName(KeyUpdatedAt) val updatedAt: String,
        @SerialName(KeyEntries) val entries: Int,
        @SerialName(KeyDataSize) val dataSize: Long,
        @SerialName(KeyFileSize) val fileSize: Long,
        @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
        @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
        @SerialName(KeyPendingTask) val pendingTask: Boolean,
        @Optional @SerialName(KeyReplicas) val replicas: List<String>? = null,
        @Optional @SerialName(KeyPrimary) val primary: String? = null,
        @Optional @SerialName(KeySourceABTest) val sourceABTest: String? = null,
        @Optional @SerialName(KeyABTest) val ABTest: JsonObject? = null
    )
}