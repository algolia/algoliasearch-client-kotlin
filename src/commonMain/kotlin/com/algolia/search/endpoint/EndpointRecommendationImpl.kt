package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.model.recommendation.PersonalizationStrategy
import com.algolia.search.model.recommendation.SetStrategyResponse
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.RouteRecommendationV2
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointRecommendationImpl(
    private val transport: Transport
) : EndpointRecommendation {

    override suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions?
    ): SetStrategyResponse {
        val body = Json.stringify(PersonalizationStrategy.serializer(), strategy)

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