/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.composition

import kotlinx.serialization.*

/**
 * Order of facet values that aren't explicitly positioned with the `order` setting.  - `count`.   Order remaining facet values by decreasing count.   The count is the number of matching records containing this facet value.  - `alpha`.   Sort facet values alphabetically.  - `hidden`.   Don't show facet values that aren't explicitly positioned.
 */
@Serializable
public enum class SortRemainingBy(public val value: kotlin.String) {

  @SerialName(value = "count")
  Count("count"),

  @SerialName(value = "alpha")
  Alpha("alpha"),

  @SerialName(value = "hidden")
  Hidden("hidden");

  override fun toString(): kotlin.String = value
}
