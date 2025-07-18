/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.abtesting

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Variant
 *
 * @param addToCartCount Number of add-to-cart events for this variant.
 * @param clickCount Number of click events for this variant.
 * @param conversionCount Number of click events for this variant.
 * @param index Index name of the A/B test variant (case-sensitive).
 * @param noResultCount Number of [searches without results](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#searches-without-results) for this variant.
 * @param purchaseCount Number of purchase events for this variant.
 * @param searchCount Number of searches for this variant.
 * @param trafficPercentage Percentage of search requests each variant receives.
 * @param userCount Number of users that made searches to this variant.
 * @param trackedUserCount Number of users that made tracked searches to this variant.
 * @param addToCartRate [Add-to-cart rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#add-to-cart-rate) for this variant.
 * @param averageClickPosition [Average click position](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#click-position) for this variant.
 * @param clickThroughRate [Click-through rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#click-through-rate) for this variant.
 * @param conversionRate [Conversion rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#conversion-rate) for this variant.
 * @param currencies A/B test currencies.
 * @param description Description for this variant.
 * @param estimatedSampleSize Estimated number of searches required to achieve the desired statistical significance.  The A/B test configuration must include a `mininmumDetectableEffect` setting for this number to be included in the response.
 * @param filterEffects
 * @param purchaseRate [Purchase rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#purchase-rate) for this variant.
 * @param trackedSearchCount Number of tracked searches. Tracked searches are search requests where the `clickAnalytics` parameter is true.
 */
@Serializable
public data class Variant(

  /** Number of add-to-cart events for this variant. */
  @SerialName(value = "addToCartCount") val addToCartCount: Int,

  /** Number of click events for this variant. */
  @SerialName(value = "clickCount") val clickCount: Int,

  /** Number of click events for this variant. */
  @SerialName(value = "conversionCount") val conversionCount: Int,

  /** Index name of the A/B test variant (case-sensitive). */
  @SerialName(value = "index") val index: String,

  /** Number of [searches without results](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#searches-without-results) for this variant. */
  @SerialName(value = "noResultCount") val noResultCount: Int,

  /** Number of purchase events for this variant. */
  @SerialName(value = "purchaseCount") val purchaseCount: Int,

  /** Number of searches for this variant. */
  @SerialName(value = "searchCount") val searchCount: Int,

  /** Percentage of search requests each variant receives. */
  @SerialName(value = "trafficPercentage") val trafficPercentage: Int,

  /** Number of users that made searches to this variant. */
  @SerialName(value = "userCount") val userCount: Int,

  /** Number of users that made tracked searches to this variant. */
  @SerialName(value = "trackedUserCount") val trackedUserCount: Int,

  /** [Add-to-cart rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#add-to-cart-rate) for this variant.  */
  @SerialName(value = "addToCartRate") val addToCartRate: Double? = null,

  /** [Average click position](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#click-position) for this variant.  */
  @SerialName(value = "averageClickPosition") val averageClickPosition: Double? = null,

  /** [Click-through rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#click-through-rate) for this variant.  */
  @SerialName(value = "clickThroughRate") val clickThroughRate: Double? = null,

  /** [Conversion rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#conversion-rate) for this variant.  */
  @SerialName(value = "conversionRate") val conversionRate: Double? = null,

  /** A/B test currencies. */
  @SerialName(value = "currencies") val currencies: Map<kotlin.String, Currency>? = null,

  /** Description for this variant. */
  @SerialName(value = "description") val description: String? = null,

  /** Estimated number of searches required to achieve the desired statistical significance.  The A/B test configuration must include a `mininmumDetectableEffect` setting for this number to be included in the response.  */
  @SerialName(value = "estimatedSampleSize") val estimatedSampleSize: Int? = null,

  @SerialName(value = "filterEffects") val filterEffects: FilterEffects? = null,

  /** [Purchase rate](https://www.algolia.com/doc/guides/search-analytics/concepts/metrics/#purchase-rate) for this variant.  */
  @SerialName(value = "purchaseRate") val purchaseRate: Double? = null,

  /** Number of tracked searches. Tracked searches are search requests where the `clickAnalytics` parameter is true. */
  @SerialName(value = "trackedSearchCount") val trackedSearchCount: Int? = null,
)
