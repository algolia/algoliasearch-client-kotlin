package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class MultipleQueriesStrategy(override val raw: String) : RawString {

    object None : MultipleQueriesStrategy(KeyNone)

    object StopIfEnoughMatches : MultipleQueriesStrategy(KeyStopIfEnoughMatches)

    data class Unknown(override val raw: String) : MultipleQueriesStrategy(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<MultipleQueriesStrategy>, Deserializer<MultipleQueriesStrategy> {

        override fun deserialize(element: JsonElement): MultipleQueriesStrategy? {
            return when (element) {
                is JsonPrimitive -> {
                    when (val content = element.contentOrNull) {
                        KeyNone -> None
                        KeyStopIfEnoughMatches -> StopIfEnoughMatches
                        null -> null
                        else -> Unknown(content)
                    }
                }
                else -> null
            }
        }
    }
}