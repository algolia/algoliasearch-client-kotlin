/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.recommend

import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import kotlin.jvm.JvmInline

/**
 * HighlightResult
 *
 * Implementations:
 * - [HighlightResultOption]
 * - [List<HighlightResultOption>] - *[HighlightResult.of]*
 * - [Map<kotlin.String, HighlightResult>] - *[HighlightResult.of]*
 * - [Map<kotlin.String, HighlightResultOption>] - *[HighlightResult.of]*
 */
@Serializable(HighlightResultSerializer::class)
public sealed interface HighlightResult {
  @Serializable
  @JvmInline
  public value class MapOfkotlinStringHighlightResultValue(public val value: Map<kotlin.String, HighlightResult>) : HighlightResult

  @Serializable
  @JvmInline
  public value class MapOfkotlinStringHighlightResultOptionValue(public val value: Map<kotlin.String, HighlightResultOption>) : HighlightResult

  @Serializable
  @JvmInline
  public value class ListOfHighlightResultOptionValue(public val value: List<HighlightResultOption>) : HighlightResult

  public companion object {

    public fun ofMapOfkotlinStringHighlightResult(value: Map<kotlin.String, HighlightResult>): HighlightResult {
      return MapOfkotlinStringHighlightResultValue(value)
    }
    public fun ofMapOfkotlinStringHighlightResultOption(value: Map<kotlin.String, HighlightResultOption>): HighlightResult {
      return MapOfkotlinStringHighlightResultOptionValue(value)
    }
    public fun of(value: List<HighlightResultOption>): HighlightResult {
      return ListOfHighlightResultOptionValue(value)
    }
  }
}

internal class HighlightResultSerializer : JsonContentPolymorphicSerializer<HighlightResult>(HighlightResult::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<HighlightResult> {
    return when {
      element is JsonObject && element.containsKey("matchLevel") && element.containsKey("matchedWords") -> HighlightResultOption.serializer()
      element is JsonObject -> HighlightResult.MapOfkotlinStringHighlightResultValue.serializer()
      element is JsonObject -> HighlightResult.MapOfkotlinStringHighlightResultOptionValue.serializer()
      element.isJsonArrayOfObjects -> HighlightResult.ListOfHighlightResultOptionValue.serializer()
      else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
    }
  }
}
