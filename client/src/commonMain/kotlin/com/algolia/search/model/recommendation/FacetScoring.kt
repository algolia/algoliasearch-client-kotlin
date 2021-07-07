package com.algolia.search.model.recommendation

import com.algolia.search.model.personalization.FacetScoring

/**
 * Configure the importance of facets.
 */
@Deprecated(
    message = "use FacetScoring from personalization package instead",
    replaceWith = ReplaceWith(
        expression = "FacetScoring",
        imports = arrayOf("com.algolia.search.model.personalization.FacetScoring")
    )
)
public typealias FacetScoring = FacetScoring
