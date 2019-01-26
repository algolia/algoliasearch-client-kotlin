package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class TopUsers(
    val topUsers: Map<ClusterName, List<Cluster>>
)