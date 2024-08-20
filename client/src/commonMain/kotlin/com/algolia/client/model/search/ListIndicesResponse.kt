/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * ListIndicesResponse
 *
 * @param items All indices in your Algolia application.
 * @param nbPages Number of pages.
 */
@Serializable
public data class ListIndicesResponse(

  /** All indices in your Algolia application. */
  @SerialName(value = "items") val items: List<FetchedIndex>,

  /** Number of pages. */
  @SerialName(value = "nbPages") val nbPages: Int? = null,
)