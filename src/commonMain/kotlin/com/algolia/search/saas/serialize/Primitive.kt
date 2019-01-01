package com.algolia.search.saas.serialize

import kotlinx.serialization.*
import kotlinx.serialization.Serializer
import kotlinx.serialization.json.*


@Serializable(Primitive.Companion::class)
internal sealed class Primitive {

    data class String(val raw: kotlin.String) : Primitive()

    data class Number(val raw: kotlin.Number) : Primitive()

    data class Boolean(val raw: kotlin.Boolean) : Primitive()

    data class List(val raw: kotlin.collections.List<Primitive>) : Primitive()

    data class Object(val raw: JsonObject) : Primitive()

    object Null : Primitive()

    @Serializer(Primitive::class)
    companion object : KSerializer<Primitive> {

        private fun deserialize(json: JsonElement): Primitive {
            return when (json) {
                is JsonNull -> Null
                is JsonObject -> Object(json)
                is JsonArray -> List(json.map(::deserialize))
                is JsonLiteral -> {
                    when {
                        json.intOrNull != null -> Number(json.int)
                        json.booleanOrNull != null -> Boolean(json.boolean)
                        else -> String(json.content)
                    }
                }
            }
        }

        private fun serialize(obj: Primitive): JsonElement {
            return when (obj) {
                is String -> JsonLiteral(obj.raw)
                is Number -> JsonLiteral(obj.raw)
                is Boolean -> JsonLiteral(obj.raw)
                is List -> JsonArray(obj.raw.map(::serialize))
                is Object -> obj.raw
                is Null -> JsonNull
            }
        }

        override fun serialize(output: Encoder, obj: Primitive) {
            val json = output as JSON.JsonOutput

            return json.writeTree(serialize(obj))
        }

        override fun deserialize(input: Decoder): Primitive {
            val json = input.readAsTree()

            return deserialize(json)
        }
    }
}