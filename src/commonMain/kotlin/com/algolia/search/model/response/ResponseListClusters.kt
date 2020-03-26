package com.algolia.search.model.response

import com.algolia.search.model.multicluster.ClusterName
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseListClusters(
    @SerialName(KeyClusters) val clusters: List<Cluster>
) {

    @Serializable
    public data class Cluster(
        /**
         * [ClusterName] of the cluster.
         */
        @SerialName(KeyClusterName) val name: ClusterName,
        /**
         * Number of records in the cluster.
         */
        @SerialName(KeyNbRecords) val nbRecords: Int,
        /**
         * Number of users assigned to the cluster.
         */
        @SerialName(KeyNbUserIDs) val nbUserIDs: Long,
        /**
         * Data size taken by all the users assigned to the cluster.
         */
        @SerialName(KeyDataSize) val dataSize: Long
    )
}
