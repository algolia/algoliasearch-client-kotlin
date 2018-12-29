package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class AlternativesAsExact(override val raw: String) : RawString {

    /**
     * Alternative words added by the [client.query.Query.ignorePlurals] feature.
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

    internal companion object : RawStringSerializer<AlternativesAsExact>, Deserializer<AlternativesAsExact> {

        override fun deserialize(element: JsonElement): AlternativesAsExact? {
            return when (element) {
                is JsonPrimitive -> when (val content = element.contentOrNull) {
                    KeyIgnorePlurals -> IgnorePlurals
                    KeySingleWordSynonym -> SingleWordSynonym
                    KeyMultiWordsSynonym -> MultiWordsSynonym
                    null -> null
                    else -> Unknown(content)
                }
                else -> null
            }
        }
    }
}