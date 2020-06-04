package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.KeyABTest
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyDataSize
import com.algolia.search.serialize.KeyEntries
import com.algolia.search.serialize.KeyFileSize
import com.algolia.search.serialize.KeyItems
import com.algolia.search.serialize.KeyLastBuildTimeS
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyNumberOfPendingTasks
import com.algolia.search.serialize.KeyPendingTask
import com.algolia.search.serialize.KeyPrimary
import com.algolia.search.serialize.KeyReplicas
import com.algolia.search.serialize.KeySourceABTest
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseListIndices(
    @SerialName(KeyItems) val items: List<Item>,
    /**
     * The value is always 1.
     * There is currently no pagination for this method. Every index is returned on the first call.
     */
    @SerialName(KeyNbPages) val nbPages: Int
) {

    @Serializable
    public data class Item(
        /**
         * Index name.
         */
        @SerialName(KeyName) val indexName: IndexName,
        /**
         * Index creation date.
         */
        @SerialName(KeyCreatedAt) val createdAt: ClientDate,
        /**
         * Date of last update.
         */
        @SerialName(KeyUpdatedAt) val updatedAt: ClientDate,
        /**
         * Number of records contained in the index.
         */
        @SerialName(KeyEntries) val entries: Int,
        /**
         * Number of bytes of the index in minified format.
         */
        @SerialName(KeyDataSize) val dataSize: Long,
        /**
         * Number of bytes of the index binary file.
         */
        @SerialName(KeyFileSize) val fileSize: Long,
        /**
         * Last build time in seconds.
         */
        @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
        /**
         * Number of pending indexing operations.
         */
        @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
        /**
         * A boolean which says whether the index has pending tasks.
         */
        @SerialName(KeyPendingTask) val pendingTask: Boolean,
        @SerialName(KeyReplicas) val replicasOrNull: List<IndexName>? = null,
        @SerialName(KeyPrimary) val primaryOrNull: IndexName? = null,
        @SerialName(KeySourceABTest) val sourceABTestOrNull: IndexName? = null,
        @SerialName(KeyABTest) val abTestOrNull: ResponseABTestShort? = null
    ) {

        public val replicas: List<IndexName>
            get() = replicasOrNull!!

        public val primary: IndexName
            get() = primaryOrNull!!

        public val sourceABTest: IndexName
            get() = sourceABTestOrNull!!

        public val abTest: ResponseABTestShort
            get() = abTestOrNull!!
    }
}
