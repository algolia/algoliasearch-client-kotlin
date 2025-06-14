/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * API request body for creating a transformation.
 *
 * @param name The uniquely identified name of your transformation.
 * @param code It is deprecated. Use the `input` field with proper `type` instead to specify the transformation code.
 * @param type
 * @param input
 * @param description A descriptive name for your transformation of what it does.
 * @param authenticationIDs The authentications associated with the current transformation.
 */
@Serializable
public data class TransformationCreate(

  /** The uniquely identified name of your transformation. */
  @SerialName(value = "name") val name: String,

  /** It is deprecated. Use the `input` field with proper `type` instead to specify the transformation code. */
  @Deprecated(message = "This property is deprecated.")
  @SerialName(value = "code") val code: String? = null,

  @SerialName(value = "type") val type: TransformationType? = null,

  @SerialName(value = "input") val input: TransformationInput? = null,

  /** A descriptive name for your transformation of what it does. */
  @SerialName(value = "description") val description: String? = null,

  /** The authentications associated with the current transformation. */
  @SerialName(value = "authenticationIDs") val authenticationIDs: List<String>? = null,
)
