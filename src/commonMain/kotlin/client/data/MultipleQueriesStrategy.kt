package client.data

import client.serialize.Deserializer
import client.serialize.KeyNone
import client.serialize.KeyStopIfEnoughMatches
import client.serialize.Serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class MultipleQueriesStrategy(open val raw: String) {

    object None : MultipleQueriesStrategy(KeyNone)

    object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    data class Unknown(override val raw: String) : MultipleQueriesStrategy(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<MultipleQueriesStrategy>, Deserializer<MultipleQueriesStrategy> {

        override fun serialize(input: MultipleQueriesStrategy): JsonElement {
            return JsonPrimitive(input.raw)
        }

        override fun deserialize(element: JsonElement): MultipleQueriesStrategy? {
            return when (val content = element.contentOrNull) {
                KeyNone -> None
                KeyStopIfEnoughMatches -> StopIfEnoughMatches
                null -> null
                else -> Unknown(content)
            }
        }
    }
}