package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.KeyDisjunctive
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyScore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


@Serializable
public data class AutomaticFacetFilters @JvmOverloads constructor(
    /**
     * [Attribute] to filter on. This must match [Pattern.Facet.attribute].
     */
    @SerialName(KeyFacet) val attribute: Attribute,
    /**
     * Score for the filter. Typically used for optional or disjunctive filters.
     */
    @SerialName(KeyScore) val score: Int? = null,
    /**
     * Whether the filter is disjunctive (true) or conjunctive (false). If the filter applies multiple times, e.g.
     * because the query string contains multiple values of the same facet, the multiple occurrences are combined
     * with an AND operator by default (conjunctive mode). If the filter is specified as disjunctive,
     * however, multiple occurrences are combined with an OR operator instead.
     */
    @SerialName(KeyDisjunctive) val disjunctive: Boolean? = null
)