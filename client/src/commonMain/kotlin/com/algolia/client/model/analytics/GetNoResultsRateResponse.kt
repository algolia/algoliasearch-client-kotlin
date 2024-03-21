/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.analytics

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * GetNoResultsRateResponse
 *
 * @param rate No results rate, calculated as number of searches with zero results divided by the total number of searches.
 * @param count Number of searches.
 * @param noResultCount Number of searches without any results.
 * @param dates Daily no results rates.
 */
@Serializable
public data class GetNoResultsRateResponse(

  /** No results rate, calculated as number of searches with zero results divided by the total number of searches. */
  @SerialName(value = "rate") val rate: Double,

  /** Number of searches. */
  @SerialName(value = "count") val count: Int,

  /** Number of searches without any results. */
  @SerialName(value = "noResultCount") val noResultCount: Int,

  /** Daily no results rates. */
  @SerialName(value = "dates") val dates: List<DailyNoResultsRates>,
)
