package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class TypoTolerance(override val raw: String) : Raw {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance(KeyMin)

    object Strict : TypoTolerance(KeyStrict)

    data class Unknown(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<TypoTolerance>, Deserializer<TypoTolerance> {

        override fun serialize(input: TypoTolerance): JsonPrimitive {
            return when (input) {
                is Boolean -> JsonPrimitive(input.boolean)
                else -> JsonPrimitive(input.raw)
            }
        }

        override fun deserialize(element: JsonElement): TypoTolerance? {
            return when (element) {
                is JsonPrimitive -> {
                    when {
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
                else -> null
            }
        }
    }
}