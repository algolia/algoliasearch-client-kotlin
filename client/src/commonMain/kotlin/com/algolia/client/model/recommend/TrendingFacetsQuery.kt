/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.recommend

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * TrendingFacetsQuery
 *
 * @param indexName Index name (case-sensitive).
 * @param threshold Minimum score a recommendation must have to be included in the response.
 * @param facetName Facet attribute for which to retrieve trending facet values.
 * @param model
 * @param maxRecommendations Maximum number of recommendations to retrieve. By default, all recommendations are returned and no fallback request is made. Depending on the available recommendations and the other request parameters, the actual number of recommendations may be lower than this value.
 * @param queryParameters
 * @param fallbackParameters
 */
@Serializable
public data class TrendingFacetsQuery(

  /** Index name (case-sensitive). */
  @SerialName(value = "indexName") val indexName: String,

  /** Minimum score a recommendation must have to be included in the response. */
  @SerialName(value = "threshold") val threshold: Double,

  /** Facet attribute for which to retrieve trending facet values. */
  @SerialName(value = "facetName") val facetName: JsonElement,

  @SerialName(value = "model") val model: TrendingFacetsModel,

  /** Maximum number of recommendations to retrieve. By default, all recommendations are returned and no fallback request is made. Depending on the available recommendations and the other request parameters, the actual number of recommendations may be lower than this value.  */
  @SerialName(value = "maxRecommendations") val maxRecommendations: Int? = null,

  @SerialName(value = "queryParameters") val queryParameters: SearchParams? = null,

  @SerialName(value = "fallbackParameters") val fallbackParameters: FallbackParams? = null,
) : RecommendationsRequest