package com.algolia.search.saas.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ClusterInfos(
    @SerialName("clusters") val clusterInfos: List<ClusterInfo>
)