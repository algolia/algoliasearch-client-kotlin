package com.algolia.search.model.recommendation

import com.algolia.search.model.personalization.PersonalizationProfileResponse

/**
 * User profile built from Personalization strategy.
 */
@Deprecated(
    message = "use PersonalizationProfileResponse from personalization package instead",
    replaceWith = ReplaceWith(
        expression = "PersonalizationProfileResponse",
        imports = arrayOf("com.algolia.search.model.personalization.PersonalizationProfileResponse")
    )
)
public typealias PersonalizationProfileResponse = PersonalizationProfileResponse
