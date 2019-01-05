package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


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

    @Serializer(AlternativesAsExact::class)
    internal companion object : KSerializer<AlternativesAsExact> {

        override fun serialize(encoder: Encoder, obj: AlternativesAsExact) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): AlternativesAsExact {
            val element = decoder.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyIgnorePlurals -> IgnorePlurals
                KeySingleWordSynonym -> SingleWordSynonym
                KeyMultiWordsSynonym -> MultiWordsSynonym
                else -> Unknown(content)
            }
        }
    }
}