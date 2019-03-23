package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyDisjunctive
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class AutomaticFacetFilters(
    @SerialName(KeyFacet) val attribute: Attribute,
    @SerialName(KeyScore) val score: Int? = null,
    @SerialName(KeyDisjunctive) val disjunctive: Boolean? = null
)