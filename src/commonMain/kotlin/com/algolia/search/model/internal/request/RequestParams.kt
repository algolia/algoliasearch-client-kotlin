package com.algolia.search.model.internal.request

import com.algolia.search.serialize.KeyParams
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestParams(
    @SerialName(KeyParams) val params: String? = null
)
