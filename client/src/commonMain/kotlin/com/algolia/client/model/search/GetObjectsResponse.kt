/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * GetObjectsResponse
 *
 * @param results Retrieved records.
 * @param message An optional status message.
 */
@Serializable
public data class GetObjectsResponse(

  /** Retrieved records. */
  @SerialName(value = "results") val results: List<JsonObject>,

  /** An optional status message. */
  @SerialName(value = "message") val message: String? = null,
)
