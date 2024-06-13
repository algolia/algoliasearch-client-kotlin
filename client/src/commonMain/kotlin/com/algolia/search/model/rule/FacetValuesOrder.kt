package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@OptIn(ExperimentalSerializationApi::class)
public data class FacetValuesOrder(
    /**
     * How to display the remaining items.
     */
    @SerialName(Key.SortRemainingBy) public val sortRemainingBy: SortRule? = null,
    /**
     * Pinned order of facet values.
     */
    @SerialName(Key.Order)
    @EncodeDefault(EncodeDefault.Mode.NEVER)
    public val order: List<String>? = null
)
