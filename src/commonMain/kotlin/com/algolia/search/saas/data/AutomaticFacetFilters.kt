package com.algolia.search.saas.data

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class AutomaticFacetFilters(
    val attribute: Attribute,
    @Optional val score: Int? = null,
    @Optional val disjunctive: Boolean? = null
)