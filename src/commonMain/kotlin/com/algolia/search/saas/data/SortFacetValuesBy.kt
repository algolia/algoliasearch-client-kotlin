package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAlpha
import com.algolia.search.saas.serialize.KeyCount
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(SortFacetValuesBy.Companion::class)
sealed class SortFacetValuesBy(override val raw: String) : Raw<String> {

    /**
     * FacetFilter values are sorted by decreasing count.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Count : SortFacetValuesBy(KeyCount)

    /**
     * FacetFilter values are sorted in alphabetical order, ascending from A to Z.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Alpha : SortFacetValuesBy(KeyAlpha)

    data class Unknown(override val raw: String) : SortFacetValuesBy(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(SortFacetValuesBy::class)
    internal companion object : KSerializer<SortFacetValuesBy> {

        override fun serialize(encoder: Encoder, obj: SortFacetValuesBy) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): SortFacetValuesBy {
            val element = decoder.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyCount -> Count
                KeyAlpha -> Alpha
                else -> Unknown(content)
            }
        }
    }
}