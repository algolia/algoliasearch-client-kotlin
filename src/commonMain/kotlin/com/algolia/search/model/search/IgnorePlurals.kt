package com.algolia.search.model.search

import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral


@Serializable(IgnorePlurals.Companion::class)
sealed class IgnorePlurals {

    data class Boolean(val boolean: kotlin.Boolean) : IgnorePlurals()

    data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : IgnorePlurals() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(IgnorePlurals::class)
    companion object : KSerializer<IgnorePlurals> {

        override fun serialize(encoder: Encoder, obj: IgnorePlurals) {
            when (obj) {
                is Boolean -> BooleanSerializer.serialize(encoder, obj.boolean)
                is QueryLanguages -> QueryLanguage.list.serialize(encoder, obj.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): IgnorePlurals {
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