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
    @SerialName(KeyName) val name: String,
    @SerialName(KeyCount) val count: Int,
    @SerialName(KeyHighlighted) val highlightedOrNull: String? = null
) {

    @Transient
    val highlighted: String
        get() = highlightedOrNull!!
}

public operator fun List<Facet>.get(name: String): Int {
    return find { it.name == name }!!.count
}


public operator fun Map<Attribute, List<Facet>>.get(attribute: Attribute, name: String): Int {
    return getValue(attribute).find { it.name == name }!!.count
}