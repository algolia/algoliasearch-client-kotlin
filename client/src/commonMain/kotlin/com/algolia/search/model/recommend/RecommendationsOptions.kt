package com.algolia.search.model.recommend

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.RecommendSearchOptions
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

/**
 * Recommendations query options.
 * [Documentation](https://www.algolia.com/doc/rest-api/recommend/#method-param-request-object)
 */
public sealed interface RecommendationsOptions {

    /** name of the index to target */
    public val indexName: IndexName

    /** the recommendation model to use */
    public val model: RecommendationModel

    /** the [ObjectID] to get recommendations for */
    public val objectID: ObjectID

    /** The threshold to use when filtering recommendations by their score, default 0, between 0 and 100 */
    public val threshold: Int?

    /** The maximum number of recommendations to retrieve */
    public val maxRecommendations: Int?

    /** search parameters to filter the recommendations */
    public val queryParameters: RecommendSearchOptions?

    /** search parameters to use as fallback when there are no recommendations */
    public val fallbackParameters: RecommendSearchOptions?
}

@Serializable
public data class RecommendationsQuery(
    override val indexName: IndexName,
    override val model: RecommendationModel,
    override val objectID: ObjectID,
    override val threshold: Int? = null,
    override val maxRecommendations: Int? = null,
    override val queryParameters: RecommendSearchOptions? = null,
    override val fallbackParameters: RecommendSearchOptions? = null,
) : RecommendationsOptions

@Serializable
public data class RelatedProducts(
    override val indexName: IndexName,
    override val objectID: ObjectID,
    override val threshold: Int? = null,
    override val maxRecommendations: Int? = null,
    override val queryParameters: RecommendSearchOptions? = null,
    override val fallbackParameters: RecommendSearchOptions? = null,
) : RecommendationsOptions {
    @Required
    override val model: RecommendationModel = RecommendationModel.RelatedProducts
}

@Serializable
public data class FrequencyBoughtTogether(
    override val indexName: IndexName,
    override val objectID: ObjectID,
    override val threshold: Int? = null,
    override val maxRecommendations: Int? = null,
    override val queryParameters: RecommendSearchOptions? = null,
    override val fallbackParameters: RecommendSearchOptions? = null,
) : RecommendationsOptions {
    @Required
    override val model: RecommendationModel = RecommendationModel.BoughtTogether
}
