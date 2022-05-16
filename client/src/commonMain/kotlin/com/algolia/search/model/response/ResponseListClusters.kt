package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseListClusters(
    @SerialName(Key.Clusters) val clusters: List<Cluster>
) {

    @Serializable
    public data class Cluster(
        /**
         * [ClusterName] of the cluster.
         */
        @SerialName(Key.ClusterName) val name: ClusterName,
        /**
         * Number of records in the cluster.
         */
        @SerialName(Key.NbRecords) val nbRecords: Int,
        /**
         * Number of users assigned to the cluster.
         */
        @SerialName(Key.NbUserIDs) val nbUserIDs: Long,
        /**
         * Data size taken by all the users assigned to the cluster.
         */
        @SerialName(Key.DataSize) val dataSize: Long
    )
}
