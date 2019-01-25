package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class UserIDs(
    val userIDs: List<Cluster>
)