package com.algolia.search.model.rule

import com.algolia.search.exception.AlgoliaClientException
import com.algolia.search.serialize.internal.isJsonArray
import com.algolia.search.serialize.internal.isString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlin.jvm.JvmInline

/**
 * Filters to promote or demote records in the search results.
 * Optional filters work like facet filters, but they don't exclude records from the search results.
 * Records that match the optional filter rank before records that don't match. If you're using a negative filter `facet:-value`, matching records rank after records that don't match.
 * - Optional filters don't work on virtual replicas.
 * - Optional filters are applied _after_ `sort-by` attributes.
 * - Optional filters don't work with numeric attributes.
 *
 * Implementations:
 * - [List<OptionalFilters>] - *[OptionalFilters()]*
 * - [String] - *[OptionalFilters()]*
 */
@Serializable(OptionalFiltersSerializer::class)
public sealed interface OptionalFilters {
    @Serializable
    @JvmInline
    public value class ListOfOptionalFiltersValue(public val value: List<OptionalFilters>) : OptionalFilters

    @Serializable
    @JvmInline
    public value class StringValue(public val value: String) : OptionalFilters

    public companion object {

        public operator fun invoke(value: List<OptionalFilters>): OptionalFilters {
            return ListOfOptionalFiltersValue(value)
        }
        public operator fun invoke(value: String): OptionalFilters {
            return StringValue(value)
        }
    }
}

internal class OptionalFiltersSerializer : JsonContentPolymorphicSerializer<OptionalFilters>(OptionalFilters::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<OptionalFilters> {
        return when {
            element.isJsonArray -> OptionalFilters.ListOfOptionalFiltersValue.serializer()
            element.isString -> OptionalFilters.StringValue.serializer()
            else -> throw AlgoliaClientException("Failed to deserialize json element: $element")
        }
    }
}
