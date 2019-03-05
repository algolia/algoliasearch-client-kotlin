package com.algolia.search.model.response.creation

import com.algolia.search.ClientDate
import com.algolia.search.model.APIKey
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreationAPIKey(
    @SerialName(KeyCreatedAt) val createdAt: ClientDate,
    @SerialName(KeyKey) val apiKey: APIKey
)