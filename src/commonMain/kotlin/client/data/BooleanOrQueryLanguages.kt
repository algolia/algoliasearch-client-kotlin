package client.data

import client.serialize.Serializer
import client.serialize.unwrap
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class BooleanOrQueryLanguages {

    data class Boolean(val boolean: kotlin.Boolean) : BooleanOrQueryLanguages()

    data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : BooleanOrQueryLanguages() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    internal companion object : Serializer<BooleanOrQueryLanguages> {

        override fun serialize(input: BooleanOrQueryLanguages?): JsonElement {
            return input.unwrap {
                when (this) {
                    is Boolean -> JsonPrimitive(boolean)
                    is QueryLanguages -> QueryLanguage.serializes(queryLanguages)
                }
            }
        }

        override fun deserialize(element: JsonElement): BooleanOrQueryLanguages? {
            return when (element) {
                is JsonArray -> QueryLanguage.deserializes(element)?.let(::QueryLanguages)
                is JsonPrimitive -> when {
                    element.booleanOrNull != null -> Boolean(element.boolean)
                    else -> null
                }
                else -> null
            }
        }
    }
}