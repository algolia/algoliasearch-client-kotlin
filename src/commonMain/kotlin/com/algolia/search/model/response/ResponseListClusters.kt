package com.algolia.search.model.response

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseListClusters(
    @SerialName(KeyClusters) val clusters: List<Cluster>
) {

    @Serializable
    public data class Cluster(
        @SerialName(KeyClusterName) val name: ClusterName,
        @SerialName(KeyNbRecords) val nbRecords: Int,
        @SerialName(KeyNbUserIDs) val nbUserIDs: Long,
        @SerialName(KeyDataSize) val dataSize: Long
    )
}
