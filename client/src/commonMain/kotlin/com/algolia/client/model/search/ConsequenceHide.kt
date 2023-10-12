/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Unique identifier of the record to hide.
 *
 * @param objectID Unique object identifier.
 */
@Serializable
public data class ConsequenceHide(

  /** Unique object identifier. */
  @SerialName(value = "objectID") val objectID: String,
)