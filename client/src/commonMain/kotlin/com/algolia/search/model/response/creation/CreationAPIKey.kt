package com.algolia.search.model.response.creation

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CreationAPIKey(
    /**
     * The created or restored [APIKey].
     */
    @SerialName(Key.Key) val apiKey: APIKey,
    /**
     * The date at which the [APIKey] has been created or restored.
     */
    @SerialName(Key.CreatedAt) val createdAt: ClientDate
)
