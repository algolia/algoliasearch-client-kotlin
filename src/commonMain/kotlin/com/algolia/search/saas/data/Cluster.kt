package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Cluster(
    val clusterName: String,
    val nbRecords: Int,
    val nbUserIDs: UserID,
    val dataSize: Long
)

