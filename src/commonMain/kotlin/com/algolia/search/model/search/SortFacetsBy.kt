package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyCount
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(SortFacetsBy.Companion::class)
public sealed class SortFacetsBy(override val raw: String) : Raw<String> {

    /**
     * [Facet.value] are sorted by decreasing [Facet.count].
     * The [Facet.count] is the number of records containing this [Facet.value] in the results of the [Query].
     */
    public object Count : SortFacetsBy(KeyCount)

    /**
     * [Facet.value] are sorted in alphabetical order, ascending from A to Z.
     * The [Facet.count] is the number of records containing this [Facet.value] in the results of the [Query].
     */
    public object Alpha : SortFacetsBy(KeyAlpha)

    public data class Other(override val raw: String) : SortFacetsBy(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<SortFacetsBy> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: SortFacetsBy) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): SortFacetsBy {
            return when (val string = serializer.deserialize(decoder)) {
                KeyCount -> Count
                KeyAlpha -> Alpha
                else -> Other(string)
            }
        }
    }
}
