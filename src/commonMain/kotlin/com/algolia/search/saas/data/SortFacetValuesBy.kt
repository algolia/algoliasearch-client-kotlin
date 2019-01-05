package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAlpha
import com.algolia.search.saas.serialize.KeyCount
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


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

    internal companion object : KSerializer<SortFacetValuesBy> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: SortFacetValuesBy) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): SortFacetValuesBy {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyCount -> Count
                KeyAlpha -> Alpha
                else -> Unknown(string)
            }
        }
    }
}