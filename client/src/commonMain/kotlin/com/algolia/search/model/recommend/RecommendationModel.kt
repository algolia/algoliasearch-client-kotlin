package com.algolia.search.model.recommend

import com.algolia.search.model.recommend.internal.RecommendationModelSerializer
import kotlinx.serialization.Serializable

/**
 * The recommendation model.
 */
@Serializable(RecommendationModelSerializer::class)
public sealed interface RecommendationModel {

    public val model: String

    public object RelatedProducts : RecommendationModel {
        override val model: String = "related-products"
    }

    public object BoughtTogether : RecommendationModel {
        override val model: String = "bought-together"
    }

    public data class Custom(override val model: String) : RecommendationModel
}
