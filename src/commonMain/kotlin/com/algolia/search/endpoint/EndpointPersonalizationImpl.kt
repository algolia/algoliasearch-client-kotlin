package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.model.personalization.PersonalizationStrategy
import com.algolia.search.model.response.ResponsePersonalizationStrategy
import com.algolia.search.model.response.revision.Revision
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.RouteRecommendation
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod

internal class EndpointPersonalizationImpl(
    private val transport: Transport
) : EndpointPersonalization {

    override suspend fun setPersonalizationStrategy(
        strategy: PersonalizationStrategy,
        requestOptions: RequestOptions?
    ): Revision {
        val body = Json.stringify(PersonalizationStrategy.serializer(), strategy)

        return transport.request(
            HttpMethod.Post,
            CallType.Write,
            "$RouteRecommendation/personalization/strategy",
            requestOptions,
            body
        )
    }

    override suspend fun getPersonalizationStrategy(requestOptions: RequestOptions?): ResponsePersonalizationStrategy {
        return transport.request(
            HttpMethod.Get,
            CallType.Read,
            "$RouteRecommendation/personalization/strategy",
            requestOptions
        )
    }
}
