package com.algolia.search.model.queryrule

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyDisjunctive
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AutomaticFacetFilters(
    @SerialName(KeyFacet) val attribute: Attribute,
    @Optional @SerialName(KeyScore) val score: Int? = null,
    @Optional @SerialName(KeyDisjunctive) val disjunctive: Boolean? = null
)