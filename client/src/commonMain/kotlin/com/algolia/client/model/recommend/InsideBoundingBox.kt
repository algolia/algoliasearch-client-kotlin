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
 * InsideBoundingBox
 *
 * Implementations:
 * - [List<List<Double>>] - *[InsideBoundingBox.of]*
 * - [String] - *[InsideBoundingBox.of]*
 */
@Serializable(InsideBoundingBoxSerializer::class)
public sealed interface InsideBoundingBox {
  @Serializable
  @JvmInline
  public value class StringValue(public val value: String) : InsideBoundingBox

  @Serializable
  @JvmInline
  public value class ListOfListOfDoubleValue(public val value: List<List<Double>>) : InsideBoundingBox

  public companion object {

    public fun of(value: String): InsideBoundingBox = StringValue(value)

    public fun of(value: List<List<Double>>): InsideBoundingBox = ListOfListOfDoubleValue(value)
  }
}

internal class InsideBoundingBoxSerializer : JsonContentPolymorphicSerializer<InsideBoundingBox>(InsideBoundingBox::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<InsideBoundingBox> = when {
    element.isString -> InsideBoundingBox.StringValue.serializer()
    element is JsonArray -> InsideBoundingBox.ListOfListOfDoubleValue.serializer()
    else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
  }
}
