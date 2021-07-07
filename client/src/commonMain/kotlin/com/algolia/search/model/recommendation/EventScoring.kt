package com.algolia.search.model.recommendation

import com.algolia.search.model.personalization.EventScoring

/**
 * Scoring the event.
 */
@Deprecated(
    message = "use EventScoring from personalization package instead",
    replaceWith = ReplaceWith(
        expression = "EventScoring",
        imports = arrayOf("com.algolia.search.model.personalization.EventScoring")
    )
)
public typealias EventScoring = EventScoring
