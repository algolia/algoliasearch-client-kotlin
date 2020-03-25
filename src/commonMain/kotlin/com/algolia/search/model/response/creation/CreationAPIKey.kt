package com.algolia.search.model.response.creation

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreationAPIKey(
    /**
     * The created or restored [APIKey].
     */
    @SerialName(KeyKey) val apiKey: APIKey,
    /**
     * The date at which the [APIKey] has been created or restored.
     */
    @SerialName(KeyCreatedAt) val createdAt: ClientDate
)
