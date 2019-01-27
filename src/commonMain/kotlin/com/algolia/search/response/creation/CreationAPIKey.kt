package com.algolia.search.response.creation

import com.algolia.search.model.APIKey
import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CreationAPIKey(
    @SerialName(KeyCreatedAt) override val date: String,
    @SerialName(KeyKey) val apiKey: APIKey
) : Datable