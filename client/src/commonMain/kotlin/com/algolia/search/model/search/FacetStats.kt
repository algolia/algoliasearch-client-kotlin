package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.internal.Key
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
    @SerialName(Key.Min) val min: Float,
    /**
     * The maximum value in the result set.
     */
    @SerialName(Key.Max) val max: Float,
    /**
     * The average facet value in the result set.
     */
    @SerialName(Key.Avg) val average: Float? = null,
    /**
     * The sum of all values in the result set.
     */
    @SerialName(Key.Sum) val sum: Float? = null
)
