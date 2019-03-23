package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class ResponseListIndices(
    @SerialName(KeyItems) val items: List<Item>,
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable
    public data class Item(
        @SerialName(KeyName) val indexName: IndexName,
        @SerialName(KeyCreatedAt) val createdAt: ClientDate,
        @SerialName(KeyUpdatedAt) val updatedAt: ClientDate,
        @SerialName(KeyEntries) val entries: Int,
        @SerialName(KeyDataSize) val dataSize: Long,
        @SerialName(KeyFileSize) val fileSize: Long,
        @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
        @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
        @SerialName(KeyPendingTask) val pendingTask: Boolean,
        @SerialName(KeyReplicas) val replicasOrNull: List<IndexName>? = null,
        @SerialName(KeyPrimary) val primaryOrNull: IndexName? = null,
        @SerialName(KeySourceABTest) val sourceABTestOrNull: IndexName? = null,
        @SerialName(KeyABTest) val abTestOrNull: ResponseABTestShort? = null
    ) {

        @Transient
        public val replicas: List<IndexName>
            get() = replicasOrNull!!

        @Transient
        public val primary: IndexName
            get() = primaryOrNull!!

        @Transient
        public val sourceABTest: IndexName
            get() = sourceABTestOrNull!!

        @Transient
        public val abTest: ResponseABTestShort
            get() = abTestOrNull!!

    }
}