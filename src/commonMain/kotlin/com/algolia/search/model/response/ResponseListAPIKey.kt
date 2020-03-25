package com.algolia.search.model.response

import com.algolia.search.serialize.KeyKeys
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseListAPIKey(
    @SerialName(KeyKeys) val keys: List<ResponseAPIKey>
)
