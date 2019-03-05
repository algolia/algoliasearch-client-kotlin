package com.algolia.search.model.request

import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.serialize.KeyDestination
import com.algolia.search.serialize.KeyOperation
import com.algolia.search.serialize.KeyScope
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class RequestCopyOrMove(
    @SerialName(KeyOperation) val operation: String,
    @SerialName(KeyDestination) val destination: IndexName,
    @Optional @SerialName(KeyScope) val scopes: List<Scope>? = null
)