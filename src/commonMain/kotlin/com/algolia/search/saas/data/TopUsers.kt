package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class TopUsers(
    val topUsers: Map<ClusterName, List<Cluster>>
)