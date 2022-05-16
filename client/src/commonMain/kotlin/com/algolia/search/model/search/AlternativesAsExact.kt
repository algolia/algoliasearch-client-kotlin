package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(AlternativesAsExact.Companion::class)
public sealed class AlternativesAsExact(override val raw: String) : Raw<String> {

    /**
     * Alternative words added by the [Query.ignorePlurals] feature.
     */
    public object IgnorePlurals : AlternativesAsExact(Key.IgnorePlurals)

    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    public object SingleWordSynonym : AlternativesAsExact(Key.SingleWordSynonym)

    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    public object MultiWordsSynonym : AlternativesAsExact(Key.MultiWordsSynonym)

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
                Key.IgnorePlurals -> IgnorePlurals
                Key.SingleWordSynonym -> SingleWordSynonym
                Key.MultiWordsSynonym -> MultiWordsSynonym
                else -> Other(string)
            }
        }
    }
}
