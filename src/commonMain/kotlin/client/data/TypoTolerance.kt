package client.data

import client.serialize.KeyMin
import client.serialize.KeyStrict
import client.serialize.Serializer
import client.serialize.unwrap
import kotlinx.serialization.json.*


sealed class TypoTolerance(open val raw: String) {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance(KeyMin)

    object Strict : TypoTolerance(KeyStrict)

    data class Unknown(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<TypoTolerance> {

        override fun serialize(input: TypoTolerance?): JsonElement {
            return input.unwrap {
                when (this) {
                    is Boolean -> JsonPrimitive(boolean)
                    else -> JsonPrimitive(raw)
                }
            }
        }

        override fun deserialize(element: JsonElement): TypoTolerance? {
            return when {
                element.booleanOrNull != null -> Boolean(element.boolean)
                else -> {
                    when (val content = element.contentOrNull) {
                        KeyMin -> Min
                        KeyStrict -> Strict
                        null -> null
                        else -> Unknown(content)
                    }
                }
            }
        }
    }
}