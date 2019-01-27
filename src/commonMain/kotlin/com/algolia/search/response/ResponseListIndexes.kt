package com.algolia.search.response

import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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
        @SerialName(KeyDataSize) val dataSize: Int,
        @SerialName(KeyFileSize) val fileSize: Int,
        @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
        @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
        @SerialName(KeyPendingTask) val pendingTask: Boolean,
        @SerialName(KeyReplicas) @Optional val replicas: List<String>? = null
    )
}