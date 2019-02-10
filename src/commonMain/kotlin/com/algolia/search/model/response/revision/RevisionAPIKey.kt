package com.algolia.search.model.response.revision

import com.algolia.search.model.APIKey
import com.algolia.search.model.Datable
import com.algolia.search.serialize.KeyKey
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RevisionAPIKey(
    @SerialName(KeyUpdatedAt) override val date: String,
    @SerialName(KeyKey) val apiKey: APIKey
) : Datable