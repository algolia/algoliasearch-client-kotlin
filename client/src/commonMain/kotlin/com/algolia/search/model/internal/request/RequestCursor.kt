package com.algolia.search.model.internal.request

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestCursor(
    @SerialName(Key.Cursor) val cursor: String? = null
)
