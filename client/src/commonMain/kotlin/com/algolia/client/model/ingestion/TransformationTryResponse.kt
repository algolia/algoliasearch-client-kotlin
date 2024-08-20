/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * TransformationTryResponse
 *
 * @param payloads The array of records returned by the transformation service.
 * @param error
 */
@Serializable
public data class TransformationTryResponse(

  /** The array of records returned by the transformation service. */
  @SerialName(value = "payloads") val payloads: List<JsonObject>,

  @SerialName(value = "error") val error: TransformationError? = null,
)