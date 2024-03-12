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
 * Determines how many records of a group are included in the search results.  Records with the same value for the `attributeForDistinct` attribute are considered a group. The `distinct` setting controls how many members of the group are returned. This is useful for [deduplication and grouping](https://www.algolia.com/doc/guides/managing-results/refine-results/grouping/#introducing-algolias-distinct-feature).  The `distinct` setting is ignored if `attributeForDistinct` is not set.
 *
 * Implementations:
 * - [Boolean] - *[Distinct.of]*
 * - [Int] - *[Distinct.of]*
 */
@Serializable(DistinctSerializer::class)
public sealed interface Distinct {
  @Serializable
  @JvmInline
  public value class BooleanValue(public val value: Boolean) : Distinct

  @Serializable
  @JvmInline
  public value class IntValue(public val value: Int) : Distinct

  public companion object {

    public fun of(value: Boolean): Distinct {
      return BooleanValue(value)
    }
    public fun of(value: Int): Distinct {
      return IntValue(value)
    }
  }
}

internal class DistinctSerializer : JsonContentPolymorphicSerializer<Distinct>(Distinct::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Distinct> {
    return when {
      element.isBoolean -> Distinct.BooleanValue.serializer()
      element.isInt -> Distinct.IntValue.serializer()
      else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
    }
  }
}
