@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointPersonalization
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.recommendation.PersonalizationProfileResponse
import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetPersonalizationStrategyResponse
import com.algolia.search.serialize.RoutePersonalization
import com.algolia.search.serialize.RouteProfiles
import com.algolia.search.serialize.internal.Json
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointPersonalizationImpl(
    private val transport: Transport,
) : EndpointPersonalization {

    override suspend fun getPersonalizationProfile(
        userToken: UserToken,
        requestOptions: RequestOptions?
    ): PersonalizationProfileResponse {
        return transport.request(
            HttpMethod.Get,
            CallType.Read,
            "$RouteProfiles/personalization/${userToken.raw}",
            requestOptions
        )
    }

    override suspend fun deletePersonalizationProfile(userToken: UserToken, requestOptions: RequestOptions?) {
        return transport.request(
            HttpMethod.Delete,
            CallType.Write,
            "$RouteProfiles/${userToken.raw}",
            requestOptions
        )
    }

    override suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions?,
    ): SetPersonalizationStrategyResponse {
        val body = Json.encodeToString(PersonalizationStrategy.serializer(), strategy)

        return transport.request(
            HttpMethod.Post,
            CallType.Write,
            RoutePersonalization,
            requestOptions,
            body
        )
    }

    override suspend fun getPersonalizationStrategy(requestOptions: RequestOptions?): PersonalizationStrategy {
        return transport.request(
            HttpMethod.Get,
            CallType.Read,
            RoutePersonalization,
            requestOptions
        )
    }
}

/**
 * Create an [EndpointPersonalization] instance.
 */
internal fun EndpointPersonalization(
    transport: Transport,
): EndpointPersonalization = EndpointPersonalizationImpl(transport)
