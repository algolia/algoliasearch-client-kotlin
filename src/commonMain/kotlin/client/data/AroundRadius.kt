package client.data

import client.serialize.Deserializer
import client.serialize.KeyAll
import client.serialize.Serializer
import kotlinx.serialization.json.*


sealed class AroundRadius(open val raw: String) {

    object All : AroundRadius(KeyAll)

    data class InMeters(val int: kotlin.Int) : AroundRadius(int.toString())

    data class Unknown(override val raw: String) : AroundRadius(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<AroundRadius>, Deserializer<AroundRadius> {

        override fun serialize(input: AroundRadius): JsonElement {
            return when (input) {
                is InMeters -> JsonPrimitive(input.int)
                else -> JsonPrimitive(input.raw)
            }
        }

        override fun deserialize(element: JsonElement): AroundRadius? {
            return when {
                element.intOrNull != null -> InMeters(element.int)
                element.contentOrNull != null -> when (element.content) {
                    KeyAll -> All
                    else -> Unknown(element.content)
                }
                else -> null
            }
        }
    }
}