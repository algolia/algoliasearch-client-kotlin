package com.algolia.search.model.internal.request

import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestCopyOrMove(
    @SerialName(Key.Operation) val operation: String,
    @SerialName(Key.Destination) val destination: IndexName,
    @SerialName(Key.Scope) val scopes: List<Scope>? = null
)
