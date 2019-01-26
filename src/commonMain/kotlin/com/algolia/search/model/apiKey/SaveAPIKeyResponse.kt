package com.algolia.search.model.apiKey

import com.algolia.search.model.APIKey
import com.algolia.search.model.common.Datable
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SaveAPIKeyResponse(
    @SerialName(KeyKey) val apiKey: APIKey,
    @SerialName(KeyCreatedAt) override val date: String
) : Datable