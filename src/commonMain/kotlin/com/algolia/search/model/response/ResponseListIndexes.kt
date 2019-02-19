package com.algolia.search.model.response

import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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
        @Optional @SerialName(KeyReplicas) val replicasOrNull: List<String>? = null, // TODO Check if IndexName
        @Optional @SerialName(KeyPrimary) val primaryOrNull: String? = null,
        @Optional @SerialName(KeySourceABTest) val sourceABTestOrNull: String? = null, // TODO Check if IndexName
        @Optional @SerialName(KeyABTest) val abTestOrNull: JsonObject? = null // TODO Check actual json format
    ) {

        @Transient
        val replicas: List<String>
            get() = replicasOrNull!!

        @Transient
        val primary: String
            get() = primaryOrNull!!

        @Transient
        val sourceABTest: String
            get() = sourceABTestOrNull!!

        @Transient
        val abTest: JsonObject
            get() = abTestOrNull!!
    }
}