package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointRecommend
import com.algolia.search.model.recommend.FrequencyBoughtTogetherQuery
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProductsQuery
import com.algolia.search.model.recommend.internal.RecommendationsRequests
import com.algolia.search.model.response.ResponseGetRecommendations
import com.algolia.search.serialize.RouteIndexesV1
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.encodeToString

internal class EndpointRecommendImpl(
    private val transport: Transport
) : EndpointRecommend {

    override suspend fun getRecommendations(
        requests: List<RecommendationsQuery>,
        requestOptions: RequestOptions?
    ): ResponseGetRecommendations {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(recommendationsRequests)
        return requestRecommendations(requestOptions, body)
    }

    override suspend fun getRelatedProducts(
        requests: List<RelatedProductsQuery>,
        requestOptions: RequestOptions?
    ): ResponseGetRecommendations {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(recommendationsRequests)
        return requestRecommendations(requestOptions, body)
    }

    override suspend fun getFrequentlyBoughtTogether(
        requests: List<FrequencyBoughtTogetherQuery>,
        requestOptions: RequestOptions?
    ): ResponseGetRecommendations {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(recommendationsRequests)
        return requestRecommendations(requestOptions, body)
    }

    private suspend fun requestRecommendations(
        requestOptions: RequestOptions?,
        body: String
    ): ResponseGetRecommendations = transport.request(
        HttpMethod.Post,
        CallType.Read,
        "$RouteIndexesV1/*/recommendations",
        requestOptions,
        body
    )
}

internal fun EndpointRecommend(transport: Transport): EndpointRecommend {
    return EndpointRecommendImpl(transport)
}
