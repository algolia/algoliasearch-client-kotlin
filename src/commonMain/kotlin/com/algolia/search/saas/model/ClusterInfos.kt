package com.algolia.search.saas.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ClusterInfos(
    @SerialName("clusters") val clusterInfos: List<ClusterInfo>
)