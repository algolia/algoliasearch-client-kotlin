package com.algolia.client.configuration.internal

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder

/**
 * Creates default [Json] instance.
 *
 * @param jsonConfig custom json configuration
 */
internal fun buildJson(jsonConfig: ((JsonBuilder) -> Unit)? = null) = Json {
  jsonConfig?.invoke(this)
  isLenient = true
  ignoreUnknownKeys = true
  allowSpecialFloatingPointValues = true
  coerceInputValues = true
}
