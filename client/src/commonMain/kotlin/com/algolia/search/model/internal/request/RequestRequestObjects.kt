package com.algolia.search.model.internal.request

import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestRequestObjects(
    @SerialName(Key.Requests) val requests: List<RequestObjects>
)
