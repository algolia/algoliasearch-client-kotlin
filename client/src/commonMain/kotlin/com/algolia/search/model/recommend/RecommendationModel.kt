package com.algolia.search.model.recommend

import com.algolia.search.model.recommend.internal.RecommendationModelSerializer
import kotlinx.serialization.Serializable

/**
 * The recommendation model.
 */
@Serializable(RecommendationModelSerializer::class)
@JvmInline
public value class RecommendationModel(public val model: String) {

    public companion object {
        public val RelatedProducts: RecommendationModel = RecommendationModel("related-products")
        public val BoughtTogether: RecommendationModel = RecommendationModel("bought-together")
    }
}
