package com.algolia.search.endpoint

import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetPersonalizationStrategyResponse
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/rest-api/recommendation]
 */
public interface EndpointRecommendation {

    /**
     * Set a [PersonalizationStrategy] for your application.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions? = null
    ): SetPersonalizationStrategyResponse

    /**
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    suspend fun getPersonalizationStrategy(
        requestOptions: RequestOptions? = null
    ): PersonalizationStrategy
}