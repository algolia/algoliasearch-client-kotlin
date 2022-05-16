package com.algolia.search.model.response.revision

import com.algolia.search.model.APIKey
import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class RevisionAPIKey(
    /**
     * The updated [APIKey].
     */
    @SerialName(Key.Key) val apiKey: APIKey,
    /**
     * The date at which the [APIKey] was updated.
     */
    @SerialName(Key.UpdatedAt) val updatedAt: ClientDate
)
