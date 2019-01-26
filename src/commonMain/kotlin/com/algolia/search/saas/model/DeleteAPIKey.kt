package com.algolia.search.saas.model

import kotlinx.serialization.Serializable


@Serializable
data class DeleteAPIKey(
    val deletedAt: String
)