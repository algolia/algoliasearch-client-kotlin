package com.algolia.search.model.search

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyHighlighted
import com.algolia.search.serialize.KeyName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class Facet(
    /**
     * Name of the facet. Is equal to the value associated to an [Attribute].
     */
    @SerialName(KeyName) val value: String,
    /**
     * Number of times this [value] occurs for a given [Attribute].
     */
    @SerialName(KeyCount) val count: Int,
    /**
     * Highlighted value.
     */
    @SerialName(KeyHighlighted) val highlightedOrNull: String? = null
) {

    @Transient
    val highlighted: String
        get() = highlightedOrNull!!
}

public operator fun List<Facet>.get(value: String): Int {
    return find { it.value == value }!!.count
}


public operator fun Map<Attribute, List<Facet>>.get(attribute: Attribute, value: String): Int {
    return getValue(attribute).find { it.value == value }!!.count
}