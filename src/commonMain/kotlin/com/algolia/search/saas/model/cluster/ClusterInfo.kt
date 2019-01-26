package com.algolia.search.saas.model.cluster

import kotlinx.serialization.Serializable


@Serializable
data class ClusterInfo(
    val clusterName: ClusterName,
    val nbRecords: Int,
    val nbUserIDs: Long,
    val dataSize: Long
)

