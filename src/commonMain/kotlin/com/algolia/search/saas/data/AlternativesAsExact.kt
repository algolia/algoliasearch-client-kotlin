package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyIgnorePlurals
import com.algolia.search.saas.serialize.KeyMultiWordsSynonym
import com.algolia.search.saas.serialize.KeySingleWordSynonym
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(AlternativesAsExact.Companion::class)
sealed class AlternativesAsExact(override val raw: String) : Raw<String> {

    /**
     * Alternative words added by the [Query.ignorePlurals] feature.
     */
    object IgnorePlurals : AlternativesAsExact(KeyIgnorePlurals)

    /**
     * Single-word synonyms (example: “NY” = “NYC”).
     */
    object SingleWordSynonym : AlternativesAsExact(KeySingleWordSynonym)

    /**
     * Multiple-words synonyms (example: “NY” = “New York”).
     */
    object MultiWordsSynonym : AlternativesAsExact(KeyMultiWordsSynonym)

    data class Unknown(override val raw: String) : AlternativesAsExact(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<AlternativesAsExact> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: AlternativesAsExact) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): AlternativesAsExact {
            val string = StringSerializer.deserialize(decoder)

            return when (string) {
                KeyIgnorePlurals -> IgnorePlurals
                KeySingleWordSynonym -> SingleWordSynonym
                KeyMultiWordsSynonym -> MultiWordsSynonym
                else -> Unknown(string)
            }
        }
    }
}