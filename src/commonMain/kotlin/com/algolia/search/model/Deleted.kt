package com.algolia.search.model

import kotlinx.serialization.Serializable


@Serializable
data class Deleted(
    val deletedAt: String
)