package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseListIndices(
    @SerialName(Key.Items) val items: List<Item>,
    /**
     * The value is always 1.
     * There is currently no pagination for this method. Every index is returned on the first call.
     */
    @SerialName(Key.NbPages) val nbPages: Int
) {

    @Serializable
    public data class Item(
        /**
         * Index name.
         */
        @SerialName(Key.Name) val indexName: IndexName,
        /**
         * Index creation date.
         */
        @SerialName(Key.CreatedAt) val createdAt: ClientDate,
        /**
         * Date of last update.
         */
        @SerialName(Key.UpdatedAt) val updatedAt: ClientDate,
        /**
         * Number of records contained in the index.
         */
        @SerialName(Key.Entries) val entries: Int,
        /**
         * Number of bytes of the index in minified format.
         */
        @SerialName(Key.DataSize) val dataSize: Long,
        /**
         * Number of bytes of the index binary file.
         */
        @SerialName(Key.FileSize) val fileSize: Long,
        /**
         * Last build time in seconds.
         */
        @SerialName(Key.LastBuildTimeS) val lastBuildTimeS: Int,
        /**
         * Number of pending indexing operations.
         */
        @SerialName(Key.NumberOfPendingTasks) val numberOfPendingTasks: Int,
        /**
         * A boolean which says whether the index has pending tasks.
         */
        @SerialName(Key.PendingTask) val pendingTask: Boolean,
        @SerialName(Key.Replicas) val replicasOrNull: List<IndexName>? = null,
        @SerialName(Key.Primary) val primaryOrNull: IndexName? = null,
        @SerialName(Key.SourceABTest) val sourceABTestOrNull: IndexName? = null,
        @SerialName(Key.ABTest) val abTestOrNull: ResponseABTestShort? = null
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
