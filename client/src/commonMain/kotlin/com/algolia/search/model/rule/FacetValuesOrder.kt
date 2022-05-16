package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetValuesOrder(
    /**
     * Pinned order of facet values.
     */
    @SerialName(Key.Order) public val order: List<String> = emptyList(),
    /**
     * How to display the remaining items.
     */
    @SerialName(Key.SortRemainingBy) public val sortRemainingBy: SortRule? = null
)
