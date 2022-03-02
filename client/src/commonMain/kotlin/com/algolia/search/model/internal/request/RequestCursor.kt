package com.algolia.search.model.internal.request

import com.algolia.search.serialize.KeyCursor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestCursor(
    @SerialName(KeyCursor) val params: String? = null
)
