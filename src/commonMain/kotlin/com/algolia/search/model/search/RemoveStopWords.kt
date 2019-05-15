package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral


@Serializable(RemoveStopWords.Companion::class)
public sealed class RemoveStopWords {

    /**
     * Enables the stop word functionality, ensuring that stop words are removed from consideration in a search.
     * The languages supported here are either every language (this is the default, see list of [QueryLanguage]),
     * or those set by queryLanguages. See queryLanguages example below.
     */
    public object True: RemoveStopWords()

    /**
     * Disables stop word functionality, allowing stop words to be taken into account in a search.
     */
    public object False: RemoveStopWords()

    /**
     * A list of [QueryLanguage] for which ignoring plurals should be enabled.
     * This list of [queryLanguages] will override any values that you may have set in [Settings.queryLanguages].
     */
    public data class QueryLanguages(val queryLanguages: List<QueryLanguage>) : RemoveStopWords() {

        constructor(vararg queryLanguage: QueryLanguage) : this(queryLanguage.toList())
    }

    @Serializer(RemoveStopWords::class)
    companion object : KSerializer<RemoveStopWords> {

        override fun serialize(encoder: Encoder, obj: RemoveStopWords) {
            when (obj) {
                is True -> BooleanSerializer.serialize(encoder, true)
                is False -> BooleanSerializer.serialize(encoder, false)
                is QueryLanguages -> QueryLanguage.list.serialize(encoder, obj.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): RemoveStopWords {
            return when (val element = decoder.asJsonInput()) {
                is JsonArray -> QueryLanguages(element.map {
                    JsonNonStrict.fromJson(QueryLanguage, it)
                })
                is JsonLiteral -> if (element.boolean) True else False
                else -> throw Exception()
            }
        }
    }
}