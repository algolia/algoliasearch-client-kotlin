package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class UserIDs(
    val userIDs: List<Cluster>
)