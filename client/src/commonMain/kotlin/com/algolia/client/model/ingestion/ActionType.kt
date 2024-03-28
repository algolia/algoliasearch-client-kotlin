/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import kotlinx.serialization.*

/**
 * Action to perform on the Algolia index.
 */
@Serializable
public enum class ActionType(public val value: kotlin.String) {

  @SerialName(value = "replace")
  Replace("replace"),

  @SerialName(value = "save")
  Save("save"),

  @SerialName(value = "partial")
  Partial("partial"),

  @SerialName(value = "append")
  Append("append");

  override fun toString(): kotlin.String = value
}
