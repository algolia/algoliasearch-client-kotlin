package com.algolia.search.model.request

import com.algolia.search.serialize.KeyParams
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RequestParams(
    @SerialName(KeyParams) val params: String? = null
)