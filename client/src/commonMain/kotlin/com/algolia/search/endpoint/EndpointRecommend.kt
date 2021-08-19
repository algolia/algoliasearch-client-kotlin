package com.algolia.search.endpoint

import com.algolia.search.model.recommend.FrequencyBoughtTogetherQuery
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProductsQuery
import com.algolia.search.model.response.ResponseGetRecommendations
import com.algolia.search.transport.RequestOptions

public interface EndpointRecommend {

    /**
     * Returns recommendations for a specific model and objectID.
     */
    public suspend fun getRecommendations(
        requests: List<RecommendationsQuery>,
        requestOptions: RequestOptions? = null
    ): ResponseGetRecommendations

    /**
     * Returns related products recommendations for a specific model and objectID.
     */
    public suspend fun getRelatedProducts(
        requests: List<RelatedProductsQuery>,
        requestOptions: RequestOptions? = null
    ): ResponseGetRecommendations

    /**
     * Returns frequently bought together recommendations for a specific model and objectID.
     */
    public suspend fun getFrequentlyBoughtTogether(
        requests: List<FrequencyBoughtTogetherQuery>,
        requestOptions: RequestOptions? = null
    ): ResponseGetRecommendations
}
