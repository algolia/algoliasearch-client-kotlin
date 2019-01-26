package com.algolia.search.model.cluster

import kotlinx.serialization.Serializable


@Serializable
data class TopUsers(
    val topUsers: Map<ClusterName, List<Cluster>>
)