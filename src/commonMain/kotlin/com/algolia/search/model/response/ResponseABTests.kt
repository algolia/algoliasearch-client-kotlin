package com.algolia.search.model.response

import com.algolia.search.model.analytics.ABTest
import com.algolia.search.serialize.KeyABTests
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyTotal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


@Serializable
public data class ResponseABTests @JvmOverloads constructor(
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

    public val abTests: List<ResponseABTest>
        get() = abTestsOrNull!!
}