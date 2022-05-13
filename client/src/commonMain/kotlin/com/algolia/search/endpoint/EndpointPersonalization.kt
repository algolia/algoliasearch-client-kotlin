package com.algolia.search.endpoint

import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.personalization.PersonalizationProfileResponse
import com.algolia.search.model.personalization.PersonalizationStrategy
import com.algolia.search.model.personalization.SetPersonalizationStrategyResponse
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/rest-api/recommendation]
 */
public interface EndpointPersonalization {

    /**
     * Get the user profile built from Personalization strategy.
     *
     * The profile is structured by facet name used in the strategy. Each facet value is mapped to its score.
     * Each score represents the user affinity for a specific facet value given the userToken past events and
     * the Personalization strategy defined. Scores are bounded to 20. The last processed event timestamp is provided
     * using the ISO 8601 format for debugging purposes.
     *
     * @param userToken userToken representing the user for which to fetch the Personalization profile
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun getPersonalizationProfile(
        userToken: UserToken,
        requestOptions: RequestOptions? = null
    ): PersonalizationProfileResponse

    /**
     * Delete the user profile and all its associated data.
     *
     * Returns, as part of the response, a date until which the data can safely be considered as deleted for the given
     * user. This means that if you send events for the given user before this date, they will be ignored.
     * Any data received after the deletedUntil date will start building a new user profile.
     *
     * It might take a couple hours before for the deletion request to be fully processed.
     *
     * @param userToken userToken representing the user for which to delete the Personalization profile and associated data.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun deletePersonalizationProfile(
        userToken: UserToken,
        requestOptions: RequestOptions? = null
    )

    /**
     * Set a [PersonalizationStrategy] for your application.
     *
     * @param strategy The personalization strategy
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions? = null
    ): SetPersonalizationStrategyResponse

    /**
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getPersonalizationStrategy(
        requestOptions: RequestOptions? = null
    ): PersonalizationStrategy
}
