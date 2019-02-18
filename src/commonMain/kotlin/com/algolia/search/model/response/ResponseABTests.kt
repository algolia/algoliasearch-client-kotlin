package com.algolia.search.model.response

import com.algolia.search.serialize.KeyABTests
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyTotal
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseABTests(
    @Optional @SerialName(KeyABTests) val abTests: List<ResponseABTest>? = null,
    @SerialName(KeyCount) val count: Int,
    @SerialName(KeyTotal) val total: Int
)