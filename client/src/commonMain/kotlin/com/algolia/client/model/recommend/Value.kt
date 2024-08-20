/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.recommend

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * Value
 *
 * @param order Explicit order of facets or facet values.  This setting lets you always show specific facets or facet values at the top of the list.
 * @param sortRemainingBy
 * @param hide Hide facet values.
 */
@Serializable
public data class Value(

  /** Explicit order of facets or facet values.  This setting lets you always show specific facets or facet values at the top of the list.  */
  @SerialName(value = "order") val order: List<String>? = null,

  @SerialName(value = "sortRemainingBy") val sortRemainingBy: SortRemainingBy? = null,

  /** Hide facet values. */
  @SerialName(value = "hide") val hide: List<String>? = null,
)