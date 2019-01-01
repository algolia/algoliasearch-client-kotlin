package client.data

import client.serialize.KeyMin
import client.serialize.KeyStrict
import client.serialize.readAsTree
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(TypoTolerance.Companion::class)
sealed class TypoTolerance(override val raw: String) : RawString {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance(KeyMin)

    object Strict : TypoTolerance(KeyStrict)

    data class Unknown(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(TypoTolerance::class)
    internal companion object : KSerializer<TypoTolerance> {

        override fun serialize(output: Encoder, obj: TypoTolerance) {
            val json = output as JSON.JsonOutput
            val element = when (obj) {
                is Boolean -> JsonPrimitive(obj.boolean)
                else -> JsonPrimitive(obj.raw)
            }

            json.writeTree(element)
        }

        override fun deserialize(input: Decoder): TypoTolerance {
            val element = input.readAsTree() as JsonLiteral

            return when {
                element.booleanOrNull != null -> Boolean(element.boolean)
                else -> {
                    when (val content = element.content) {
                        KeyMin -> Min
                        KeyStrict -> Strict
                        else -> Unknown(content)
                    }
                }
            }
        }
    }
}