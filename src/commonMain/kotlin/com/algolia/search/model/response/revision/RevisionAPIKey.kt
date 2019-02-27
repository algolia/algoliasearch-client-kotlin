package com.algolia.search.model.response.revision

import com.algolia.search.model.ClientDate
import com.algolia.search.model.APIKey
import com.algolia.search.serialize.KeyKey
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RevisionAPIKey(
    @SerialName(KeyUpdatedAt) val updatedAt: ClientDate,
    @SerialName(KeyKey) val apiKey: APIKey
)