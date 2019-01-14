package com.algolia.search.saas.data

import kotlinx.serialization.Serializable


@Serializable
data class DeleteAPIKey(
    val deletedAt: String
)