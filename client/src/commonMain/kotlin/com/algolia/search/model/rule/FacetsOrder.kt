package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Define or override the way facet attributes are displayed.
 */
@Serializable
public data class FacetsOrder(
    /**
     * Pinned order of facet lists.
     */
    @SerialName(Key.Order) public val order: List<String>,
)
