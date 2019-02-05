package com.algolia.search.model.response

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseListClusters(
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
