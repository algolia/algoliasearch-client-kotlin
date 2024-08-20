/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.search

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Turn on or off the built-in Algolia stop words for a specific language.
 *
 * @param disableStandardEntries
 */
@Serializable
public data class DictionarySettingsParams(

  @SerialName(value = "disableStandardEntries") val disableStandardEntries: StandardEntries,
)