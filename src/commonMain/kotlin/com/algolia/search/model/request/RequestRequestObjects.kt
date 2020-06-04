package com.algolia.search.model.request

import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.serialize.KeyRequests
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RequestRequestObjects(
    @SerialName(KeyRequests) val requests: List<RequestObjects>
)
