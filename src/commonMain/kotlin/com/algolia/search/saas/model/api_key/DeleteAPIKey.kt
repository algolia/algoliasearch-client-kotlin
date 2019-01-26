package com.algolia.search.saas.model.api_key

import kotlinx.serialization.Serializable


@Serializable
data class DeleteAPIKey(
    val deletedAt: String
)