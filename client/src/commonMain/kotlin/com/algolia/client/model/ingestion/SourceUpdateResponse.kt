/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * SourceUpdateResponse
 *
 * @param sourceID Universally uniqud identifier (UUID) of a source.
 * @param name Descriptive name of the source.
 * @param updatedAt Date of last update in RFC3339 format.
 */
@Serializable
public data class SourceUpdateResponse(

  /** Universally uniqud identifier (UUID) of a source. */
  @SerialName(value = "sourceID") val sourceID: String,

  /** Descriptive name of the source. */
  @SerialName(value = "name") val name: String,

  /** Date of last update in RFC3339 format. */
  @SerialName(value = "updatedAt") val updatedAt: String,
)
