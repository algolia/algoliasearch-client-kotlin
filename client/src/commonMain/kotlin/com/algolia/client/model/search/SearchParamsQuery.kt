/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * SearchParamsQuery
 *
 * @param query Text to search for in an index.
 */
@Serializable
public data class SearchParamsQuery(

  /** Text to search for in an index. */
  @SerialName(value = "query") val query: String? = null,
)