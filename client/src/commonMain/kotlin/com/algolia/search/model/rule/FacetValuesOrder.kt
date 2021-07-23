package com.algolia.search.model.rule

import com.algolia.search.serialize.KeyOrder
import com.algolia.search.serialize.KeySortRemainingBy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FacetValuesOrder(
    /**
     * Pinned order of facet values.
     */
    @SerialName(KeyOrder) public val order: List<String>,
    /**
     * How to display the remaining items.
     */
    @SerialName(KeySortRemainingBy) public val sortRemainingBy: SortRule? = null
)
