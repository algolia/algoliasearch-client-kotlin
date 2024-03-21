/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.analytics

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * TopCountry
 *
 * @param country Country code.
 * @param count Number of occurrences.
 */
@Serializable
public data class TopCountry(

  /** Country code. */
  @SerialName(value = "country") val country: String,

  /** Number of occurrences. */
  @SerialName(value = "count") val count: Int,
)
