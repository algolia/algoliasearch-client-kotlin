package com.algolia.search.model.response

import com.algolia.search.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
data class ResponseListIndexes(
    @SerialName(KeyItems) val items: List<Item>,
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable
    data class Item(
        @SerialName(KeyName) val indexName: IndexName,
        @SerialName(KeyCreatedAt) val createdAt: ClientDate,
        @SerialName(KeyUpdatedAt) val updatedAt: ClientDate,
        @SerialName(KeyEntries) val entries: Int,
        @SerialName(KeyDataSize) val dataSize: Long,
        @SerialName(KeyFileSize) val fileSize: Long,
        @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
        @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
        @SerialName(KeyPendingTask) val pendingTask: Boolean,
        @Optional @SerialName(KeyReplicas) val replicasOrNull: List<IndexName>? = null, // TODO Check if IndexName
        @Optional @SerialName(KeyPrimary) val primaryOrNull: IndexName? = null,
        @Optional @SerialName(KeySourceABTest) val sourceABTestOrNull: IndexName? = null, // TODO Check if IndexName
        @Optional @SerialName(KeyABTest) val abTestOrNull: ResponseABTestShort? = null // TODO Check actual json format
    ) {

        @Transient
        val replicas: List<IndexName>
            get() = replicasOrNull!!

        @Transient
        val primary: IndexName
            get() = primaryOrNull!!

        @Transient
        val sourceABTest: IndexName
            get() = sourceABTestOrNull!!

        @Transient
        val abTest: ResponseABTestShort
            get() = abTestOrNull!!

    }
}