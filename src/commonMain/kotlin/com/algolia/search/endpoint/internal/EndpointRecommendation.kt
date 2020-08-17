@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointRecommendation
import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetPersonalizationStrategyResponse
import com.algolia.search.serialize.RouteRecommendationV2
import com.algolia.search.serialize.internal.Json
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointRecommendationImpl(
    private val transport: Transport,
) : EndpointRecommendation {

    override suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions?,
    ): SetPersonalizationStrategyResponse {
        val body = Json.encodeToString(PersonalizationStrategy.serializer(), strategy)

        return transport.request(
            HttpMethod.Post,
            CallType.Write,
            RouteRecommendationV2,
            requestOptions,
            body
        )
    }

    override suspend fun getPersonalizationStrategy(requestOptions: RequestOptions?): PersonalizationStrategy {
        return transport.request(
            HttpMethod.Get,
            CallType.Read,
            RouteRecommendationV2,
            requestOptions
        )
    }
}

/**
 * Create an [EndpointRecommendation] instance.
 */
internal fun EndpointRecommendation(
    transport: Transport,
): EndpointRecommendation = EndpointRecommendationImpl(transport)
