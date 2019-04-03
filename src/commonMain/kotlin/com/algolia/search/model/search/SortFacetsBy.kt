package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(SortFacetsBy.Companion::class)
public sealed class SortFacetsBy(override val raw: String) : Raw<String> {

    /**
     * FacetFilter values are sorted by decreasing count.
     * The count is the number of records containing this facet value in the results of the query.
     */
    public object Count : SortFacetsBy(KeyCount)

    /**
     * FacetFilter values are sorted in alphabetical order, ascending from A to Z.
     * The count is the number of records containing this facet value in the results of the query.
     */
    public object Alpha : SortFacetsBy(KeyAlpha)

    public data class Other(override val raw: String) : SortFacetsBy(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<SortFacetsBy> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: SortFacetsBy) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): SortFacetsBy {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyCount -> Count
                KeyAlpha -> Alpha
                else -> Other(string)
            }
        }
    }
}