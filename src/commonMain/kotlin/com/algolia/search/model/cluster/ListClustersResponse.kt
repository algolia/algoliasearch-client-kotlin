package com.algolia.search.model.cluster

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListClustersResponse(
    @SerialName(KeyClusters) val infos: List<Info>
) {

    @Serializable
    data class Info(
        @SerialName(KeyClusterName) val clusterName: ClusterName,
        @SerialName(KeyNbRecords) val nbRecords: Int,
        @SerialName(KeyNbUserIDs) val nbUserIDs: Long,
        @SerialName(KeyDataSize) val dataSize: Long
    )
}