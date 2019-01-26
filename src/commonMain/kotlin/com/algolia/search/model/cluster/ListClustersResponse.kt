package com.algolia.search.model.cluster

import com.algolia.search.serialize.KeyClusters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListClustersResponse(
    @SerialName(KeyClusters) val clusterResponses: List<ClusterResponse>
)