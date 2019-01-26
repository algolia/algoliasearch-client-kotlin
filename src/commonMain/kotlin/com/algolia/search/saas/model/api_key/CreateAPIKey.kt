package com.algolia.search.saas.model.api_key

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreateAPIKey(
    @SerialName("key") val apiKey: APIKey,
    val createdAt: String
)