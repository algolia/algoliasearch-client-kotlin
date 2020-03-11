package com.algolia.search.endpoint

import com.algolia.search.model.personalization.PersonalizationStrategy
import com.algolia.search.model.response.ResponsePersonalizationStrategy
import com.algolia.search.model.response.revision.Revision
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/personalization/?language=kotlin]
 */
public interface EndpointPersonalization {

    @Deprecated(
        message = "Endpoint will be deprecated, please use RecommendationClient instead.",
        level = DeprecationLevel.WARNING
    )
    /**
     * Set a [PersonalizationStrategy] for your application.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions? = null
    ): Revision

    @Deprecated(
        message = "Endpoint will be deprecated, please use RecommendationClient instead.",
        level = DeprecationLevel.WARNING
    )
    /**
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun getPersonalizationStrategy(
        requestOptions: RequestOptions? = null
    ): ResponsePersonalizationStrategy
}
