package com.algolia.search.model.cluster

import kotlinx.serialization.Serializable


@Serializable
data class UserIDs(
    val userIDs: List<Cluster>
)