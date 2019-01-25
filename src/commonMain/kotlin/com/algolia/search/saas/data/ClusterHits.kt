package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class ClusterHits(
    val hits: List<Cluster>,
    val nbHits: Int,
    val page: Int,
    val hitsPerPage: Int,
    val updatedAt: String
)