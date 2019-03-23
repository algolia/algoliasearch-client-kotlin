package com.algolia.search.model.request

import com.algolia.search.serialize.KeyParams
import kotlinx.serialization.*


@Serializable
data class RequestParams(
    @SerialName(KeyParams) val params: String? = null
)