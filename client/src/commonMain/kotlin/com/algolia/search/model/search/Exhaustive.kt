package com.algolia.search.model.search

import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Whether certain properties of the search response are calculated exhaustive (exact) or approximated.
 *
 * List of fields:
 *
 * `facetsCount`: Whether the facet count is exhaustive (true) or approximate (false). See the [related discussion](https://support.algolia.com/hc/en-us/articles/4406975248145-Why-are-my-facet-and-hit-counts-not-accurate-).
 * `facetValues`: The value is false if not all facet values are retrieved.
 * `nbHits`: Whether the `nbHits` is exhaustive (true) or approximate (false). When the query takes more than 50ms to be processed, the engine makes an approximation. This can happen when using complex filters on millions of records, when typo-tolerance was not exhaustive, or when enough hits have been retrieved (for example, after the engine finds 10,000 exact matches). `nbHits` is reported as non-exhaustive whenever an approximation is made, even if the approximation didn't, in the end, impact the exhaustiveness of the query.
 * `rulesMatch`: Rules matching exhaustiveness. The value is false if rules were enabled for this query, and could not be fully processed due a timeout. This is generally caused by the number of alternatives (such as typos) which is too large.
 * `typo`: Whether the `typo` search was exhaustive (true) or approximate (false). An approximation is done when the typo search query part takes more than 10% of the query budget (i.e., 5ms by default) to be processed (this can happen when a lot of typo alternatives exist for the query). This field will not be included when typo-tolerance is entirely disabled.
 */
@Serializable
public data class Exhaustive(
    @SerialName(Key.FacetsCount) val facetsCount: Boolean? = null,
    @SerialName(Key.FacetValues) val facetValues: Boolean? = null,
    @SerialName(Key.NbHits) val nbHits: Boolean,
    @SerialName(Key.RulesMatch) val rulesMatch: Boolean? = null,
    @SerialName(Key.Typo) val typo: Boolean? = null
) {

}
