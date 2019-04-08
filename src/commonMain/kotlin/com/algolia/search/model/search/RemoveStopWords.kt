package com.algolia.search.model.search

import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral


@Serializable(RemoveStopWords.Companion::class)
public sealed class RemoveStopWords {

    public data class Boolean(val boolean: kotlin.Boolean) : RemoveStopWords()

    public data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : RemoveStopWords() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(RemoveStopWords::class)
    internal companion object : KSerializer<RemoveStopWords> {

        override fun serialize(encoder: Encoder, obj: RemoveStopWords) {
            when (obj) {
                is Boolean -> BooleanSerializer.serialize(encoder, obj.boolean)
                is QueryLanguages -> QueryLanguage.list.serialize(encoder, obj.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): RemoveStopWords {
            return when (val element = decoder.asJsonInput()) {
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