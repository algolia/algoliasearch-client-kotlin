package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.endpoint.EndpointRecommend
import com.algolia.search.model.recommend.FrequentlyBoughtTogetherQuery
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProductsQuery
import com.algolia.search.model.recommend.internal.RecommendationsRequests
import com.algolia.search.model.recommend.internal.RecommendationsResponse
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Route
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod

internal class EndpointRecommendImpl(
    private val transport: Transport
) : EndpointRecommend {

    override suspend fun getRecommendations(
        requests: List<RecommendationsQuery>,
        requestOptions: RequestOptions?
    ): List<ResponseSearch> {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(
            RecommendationsRequests.serializer(RecommendationsQuery.serializer()),
            recommendationsRequests
        )
        return requestRecommendations(requestOptions, body).results
    }

    override suspend fun getRelatedProducts(
        requests: List<RelatedProductsQuery>,
        requestOptions: RequestOptions?
    ): List<ResponseSearch> {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(
            RecommendationsRequests.serializer(RelatedProductsQuery.serializer()),
            recommendationsRequests
        )
        return requestRecommendations(requestOptions, body).results
    }

    override suspend fun getFrequentlyBoughtTogether(
        requests: List<FrequentlyBoughtTogetherQuery>,
        requestOptions: RequestOptions?
    ): List<ResponseSearch> {
        val recommendationsRequests = RecommendationsRequests(requests)
        val body = JsonNoDefaults.encodeToString(
            RecommendationsRequests.serializer(FrequentlyBoughtTogetherQuery.serializer()),
            recommendationsRequests
        )
        return requestRecommendations(requestOptions, body).results
    }

    private suspend fun requestRecommendations(
        requestOptions: RequestOptions?,
        body: String
    ): RecommendationsResponse = transport.request(
        HttpMethod.Post,
        CallType.Read,
        "${Route.IndexesV1}/*/recommendations",
        requestOptions,
        body
    )
}

internal fun EndpointRecommend(transport: Transport): EndpointRecommend {
    return EndpointRecommendImpl(transport)
}
