package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.KeyAvg
import com.algolia.search.serialize.KeyMax
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeySum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Regardless of the number of requested facet values (as per [Query.maxValuesPerFacet] [Settings.maxValuesPerFacet]),
 * statistics are always computed on at most 1,000 distinct values (starting with the most frequent ones).
 * If there are more than 1,000 distinct values, statistics may therefore not be 100% accurate.
 */
@Serializable
public data class FacetStats(
    /**
     * The minimum value in the result set.
     */
    @SerialName(KeyMin) val min: Float,
    /**
     * The maximum value in the result set.
     */
    @SerialName(KeyMax) val max: Float,
    /**
     * The average facet value in the result set.
     */
    @SerialName(KeyAvg) val average: Float? = null,
    /**
     * The sum of all values in the result set.
     */
    @SerialName(KeySum) val sum: Float? = null
)
