package com.algolia.search.model.cluster

import com.algolia.search.model.ClusterName
import com.algolia.search.serialize.KeyClusterName
import com.algolia.search.serialize.KeyDataSize
import com.algolia.search.serialize.KeyNbRecords
import com.algolia.search.serialize.KeyNbUserIDs
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ClusterResponse(
    @SerialName(KeyClusterName) val clusterName: ClusterName,
    @SerialName(KeyNbRecords) val nbRecords: Int,
    @SerialName(KeyNbUserIDs) val nbUserIDs: Long,
    @SerialName(KeyDataSize) val dataSize: Long
)

