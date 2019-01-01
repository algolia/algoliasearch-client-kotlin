package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.readAsTree
import kotlinx.serialization.*
import kotlinx.serialization.json.*


@Serializable(BooleanOrQueryLanguages.Companion::class)
sealed class BooleanOrQueryLanguages {

    data class Boolean(val boolean: kotlin.Boolean) : BooleanOrQueryLanguages()

    data class QueryLanguages(val queryLanguages: List<QueryLanguage>) :
        BooleanOrQueryLanguages() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(BooleanOrQueryLanguages::class)
    internal companion object : KSerializer<BooleanOrQueryLanguages> {

        override fun serialize(output: Encoder, obj: BooleanOrQueryLanguages) {
            val json = output as JSON.JsonOutput
            val element = when (obj) {
                is Boolean -> JsonPrimitive(obj.boolean)
                is QueryLanguages -> jsonArray { obj.queryLanguages.forEach { +it.raw } }
            }

            json.writeTree(element)
        }

        override fun deserialize(input: Decoder): BooleanOrQueryLanguages {
            val element = input.readAsTree()

            return when (element) {
                is JsonArray -> QueryLanguages(element.map { QueryLanguage.convert(it.content) })
                is JsonLiteral -> Boolean(element.boolean)
                else -> throw Exception()
            }
        }
    }
}