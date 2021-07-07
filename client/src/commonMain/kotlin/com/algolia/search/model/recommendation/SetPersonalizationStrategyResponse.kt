package com.algolia.search.model.recommendation

import com.algolia.search.model.personalization.SetPersonalizationStrategyResponse

/**
 *  Set strategy response
 */
@Deprecated(
    message = "use SetPersonalizationStrategyResponse from personalization package instead",
    replaceWith = ReplaceWith(
        expression = "SetPersonalizationStrategyResponse",
        imports = arrayOf("com.algolia.search.model.personalization.SetPersonalizationStrategyResponse")
    )
)
public typealias SetPersonalizationStrategyResponse = SetPersonalizationStrategyResponse
