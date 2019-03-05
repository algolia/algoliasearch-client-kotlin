package com.algolia.search.model.search

import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral


@Serializable(BooleanOrQueryLanguages.Companion::class)
sealed class BooleanOrQueryLanguages {

    data class Boolean(val boolean: kotlin.Boolean) : BooleanOrQueryLanguages()

    data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : BooleanOrQueryLanguages() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(BooleanOrQueryLanguages::class)
    companion object : KSerializer<BooleanOrQueryLanguages> {

        override fun serialize(encoder: Encoder, obj: BooleanOrQueryLanguages) {
            when (obj) {
                is Boolean -> BooleanSerializer.serialize(encoder, obj.boolean)
                is QueryLanguages -> QueryLanguage.list.serialize(encoder, obj.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): BooleanOrQueryLanguages {
            val element = decoder.asJsonInput()

            return when (element) {
                is JsonArray -> QueryLanguages(element.map {
                    Json.nonstrict.fromJson(
                        QueryLanguage,
                        it
                    )
                })
                is JsonLiteral -> Boolean(element.boolean)
                else -> throw Exception()
            }
        }
    }
}