/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.personalization

import kotlinx.serialization.*
import kotlinx.serialization.json.*

/**
 * EventScoring
 *
 * @param score Event score.
 * @param eventName Event name.
 * @param eventType
 */
@Serializable
public data class EventScoring(

  /** Event score. */
  @SerialName(value = "score") val score: Int,

  /** Event name. */
  @SerialName(value = "eventName") val eventName: String,

  @SerialName(value = "eventType") val eventType: EventType,
)