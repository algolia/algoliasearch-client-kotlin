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
 * Filter the search by facet values, so that only records with the same facet values are retrieved.  **Prefer using the `filters` parameter, which supports all filter types and combinations with boolean operators.**  - `[filter1, filter2]` is interpreted as `filter1 AND filter2`. - `[[filter1, filter2], filter3]` is interpreted as `filter1 OR filter2 AND filter3`. - `facet:-value` is interpreted as `NOT facet:value`.  While it's best to avoid attributes that start with a `-`, you can still filter them by escaping with a backslash: `facet:\\-value`.
 *
 * Implementations:
 * - [List<FacetFilters>] - *[FacetFilters.of]*
 * - [String] - *[FacetFilters.of]*
 */
@Serializable(FacetFiltersSerializer::class)
public sealed interface FacetFilters {
  @Serializable
  @JvmInline
  public value class ListOfFacetFiltersValue(public val value: List<FacetFilters>) : FacetFilters

  @Serializable
  @JvmInline
  public value class StringValue(public val value: String) : FacetFilters

  public companion object {

    public fun of(value: List<FacetFilters>): FacetFilters {
      return ListOfFacetFiltersValue(value)
    }
    public fun of(value: String): FacetFilters {
      return StringValue(value)
    }
  }
}

internal class FacetFiltersSerializer : JsonContentPolymorphicSerializer<FacetFilters>(FacetFilters::class) {
  override fun selectDeserializer(element: JsonElement): DeserializationStrategy<FacetFilters> {
    return when {
      element.isJsonArrayOfObjects -> FacetFilters.ListOfFacetFiltersValue.serializer()
      element.isString -> FacetFilters.StringValue.serializer()
      else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
    }
  }
}
