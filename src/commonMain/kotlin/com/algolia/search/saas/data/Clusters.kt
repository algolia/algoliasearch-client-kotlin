package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Clusters(
    val clusters: List<Cluster>
)