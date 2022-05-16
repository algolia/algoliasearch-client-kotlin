package com.algolia.search.model.response

import com.algolia.search.model.analytics.ABTest
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseABTests(
    /**
     * Number of [ABTest] returned.
     */
    @SerialName(Key.Count) val count: Int,
    /**
     * Total number of [ABTest] that can be fetched.
     */
    @SerialName(Key.Total) val total: Int,
    @SerialName(Key.ABTests) val abTestsOrNull: List<ResponseABTest>? = null
) {

    public val abTests: List<ResponseABTest>
        get() = abTestsOrNull!!
}
