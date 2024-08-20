/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * API response for the successful creation of an authentication resource.
 *
 * @param authenticationID Universally unique identifier (UUID) of an authentication resource.
 * @param name Descriptive name for the resource.
 * @param createdAt Date of creation in RFC 3339 format.
 */
@Serializable
public data class AuthenticationCreateResponse(

  /** Universally unique identifier (UUID) of an authentication resource. */
  @SerialName(value = "authenticationID") val authenticationID: String,

  /** Descriptive name for the resource. */
  @SerialName(value = "name") val name: String,

  /** Date of creation in RFC 3339 format. */
  @SerialName(value = "createdAt") val createdAt: String,
)