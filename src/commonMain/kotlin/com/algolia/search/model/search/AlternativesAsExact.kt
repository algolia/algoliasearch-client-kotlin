package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyMultiWordsSynonym
import com.algolia.search.serialize.KeySingleWordSynonym
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

@Serializable(AlternativesAsExact.Companion::class)
public sealed class AlternativesAsExact(override val raw: String) : Raw<String> {

    /**
     * Alternative words added by the [Query.ignorePlurals] feature.
     */
    public object IgnorePlurals : AlternativesAsExact(KeyIgnorePlurals)

    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    public object SingleWordSynonym : AlternativesAsExact(KeySingleWordSynonym)

    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    public object MultiWordsSynonym : AlternativesAsExact(KeyMultiWordsSynonym)

    public data class Other(override val raw: String) : AlternativesAsExact(raw)

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<AlternativesAsExact> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: AlternativesAsExact) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): AlternativesAsExact {
            return when (val string = serializer.deserialize(decoder)) {
                KeyIgnorePlurals -> IgnorePlurals
                KeySingleWordSynonym -> SingleWordSynonym
                KeyMultiWordsSynonym -> MultiWordsSynonym
                else -> Other(string)
            }
        }
    }
}
