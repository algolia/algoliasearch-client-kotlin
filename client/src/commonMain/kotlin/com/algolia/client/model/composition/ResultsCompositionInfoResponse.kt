/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.composition

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * ResultsCompositionInfoResponse
 *
 * @param injectedItems
 */
@Serializable
public data class ResultsCompositionInfoResponse(

  @SerialName(value = "injectedItems") val injectedItems: List<ResultsInjectedItemInfoResponse>,
)
