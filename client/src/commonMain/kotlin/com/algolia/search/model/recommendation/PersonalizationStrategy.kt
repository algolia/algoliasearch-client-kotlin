package com.algolia.search.model.recommendation

import com.algolia.search.model.personalization.PersonalizationStrategy

/**
 *  Strategy Payload
 */
@Deprecated(
    message = "use PersonalizationStrategy from personalization package instead",
    replaceWith = ReplaceWith(
        expression = "PersonalizationStrategy",
        imports = arrayOf("com.algolia.search.model.personalization.PersonalizationStrategy")
    )
)
public typealias PersonalizationStrategy = PersonalizationStrategy
