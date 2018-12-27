package client.data

import client.serialize.KeyIgnorePlurals
import client.serialize.KeyMultiWordsSynonym
import client.serialize.KeySingleWordSynonym
import client.serialize.Serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class AlternativesAsExact(open val raw: String) {

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

    internal companion object : Serializer<AlternativesAsExact> {

        override fun serialize(input: AlternativesAsExact?): JsonElement {
            return if (input != null) JsonPrimitive(input.raw) else JsonNull
        }

        override fun deserialize(element: JsonElement): AlternativesAsExact? {
            return when (val content = element.contentOrNull) {
                KeyIgnorePlurals -> IgnorePlurals
                KeySingleWordSynonym -> SingleWordSynonym
                KeyMultiWordsSynonym -> MultiWordsSynonym
                null -> null
                else -> Unknown(content)
            }
        }
    }
}