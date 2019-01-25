package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class Deleted(
    val deletedAt: String
)