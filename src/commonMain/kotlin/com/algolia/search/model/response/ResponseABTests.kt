package com.algolia.search.model.response

import com.algolia.search.serialize.KeyABTests
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyTotal
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class ResponseABTests(
    @Optional @SerialName(KeyABTests) val abTestsOrNull: List<ResponseABTest>? = null,
    @SerialName(KeyCount) val count: Int,
    @SerialName(KeyTotal) val total: Int
) {

    @Transient
    public val abTests: List<ResponseABTest>
        get() = abTestsOrNull!!
}