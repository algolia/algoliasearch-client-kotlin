package com.algolia.search.model.rule

import com.algolia.search.model.Attribute
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AutomaticFacetFilters(
    /**
     * [Attribute] to filter on. This must match [Pattern.Facet.attribute].
     */
    @SerialName(Key.Facet) val attribute: Attribute,
    /**
     * Score for the filter. Typically used for optional or disjunctive filters.
     */
    @SerialName(Key.Score) val score: Int? = null,
    /**
     * Whether the filter is disjunctive (true) or conjunctive (false). If the filter applies multiple times, e.g.
     * because the query string contains multiple values of the same facet, the multiple occurrences are combined
     * with an AND operator by default (conjunctive mode). If the filter is specified as disjunctive,
     * however, multiple occurrences are combined with an OR operator instead.
     */
    @SerialName(Key.Disjunctive) val disjunctive: Boolean? = null
)
