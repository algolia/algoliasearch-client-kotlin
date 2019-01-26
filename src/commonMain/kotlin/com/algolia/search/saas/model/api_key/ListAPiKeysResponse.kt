package com.algolia.search.saas.model.api_key

import com.algolia.search.saas.serialize.KeyKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ListAPiKeysResponse(
    @SerialName(KeyKeys) val keys: List<APIKeyResponse>
)