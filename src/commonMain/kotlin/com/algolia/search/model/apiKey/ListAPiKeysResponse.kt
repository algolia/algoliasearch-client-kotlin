package com.algolia.search.model.apiKey

import com.algolia.search.serialize.KeyKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListAPiKeysResponse(
    @SerialName(KeyKeys) val keys: List<APIKeyResponse>
)