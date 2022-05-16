package com.algolia.search.model.response

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseListAPIKey(
    @SerialName(Key.Keys) val keys: List<ResponseAPIKey>
)
