package com.algolia.search.model.response

import com.algolia.search.model.analytics.ABTest
import com.algolia.search.serialize.KeyABTests
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyTotal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseABTests(
    /**
     * Number of [ABTest] returned.
     */
    @SerialName(KeyCount) val count: Int,
    /**
     * Total number of [ABTest] that can be fetched.
     */
    @SerialName(KeyTotal) val total: Int,
    @SerialName(KeyABTests) val abTestsOrNull: List<ResponseABTest>? = null
) {

    val abTests: List<ResponseABTest>
        get() = abTestsOrNull!!
}
