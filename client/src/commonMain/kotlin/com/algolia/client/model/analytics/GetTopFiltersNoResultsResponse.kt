/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.analytics

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * GetTopFiltersNoResultsResponse
 *
 * @param values Filters for searches without any results. If null, the search term specified with the `search` parameter isn't a search without results, or the `search` parameter is absent from the request.
 */
@Serializable
public data class GetTopFiltersNoResultsResponse(

  /** Filters for searches without any results. If null, the search term specified with the `search` parameter isn't a search without results, or the `search` parameter is absent from the request.  */
  @SerialName(value = "values") val values: List<GetTopFiltersNoResultsValues>,
)
