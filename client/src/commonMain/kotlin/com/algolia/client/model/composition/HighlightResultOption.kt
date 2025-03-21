/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.composition

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Surround words that match the query with HTML tags for highlighting.
 *
 * @param `value` Highlighted attribute value, including HTML tags.
 * @param matchLevel
 * @param matchedWords List of matched words from the search query.
 * @param fullyHighlighted Whether the entire attribute value is highlighted.
 */
@Serializable
public data class HighlightResultOption(

  /** Highlighted attribute value, including HTML tags. */
  @SerialName(value = "value") val `value`: String,

  @SerialName(value = "matchLevel") val matchLevel: MatchLevel,

  /** List of matched words from the search query. */
  @SerialName(value = "matchedWords") val matchedWords: List<String>,

  /** Whether the entire attribute value is highlighted. */
  @SerialName(value = "fullyHighlighted") val fullyHighlighted: Boolean? = null,
) : HighlightResult
