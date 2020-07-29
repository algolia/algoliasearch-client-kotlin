package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.JsonNonStrict
import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral

@Serializable(RemoveStopWords.Companion::class)
public sealed class RemoveStopWords {

    /**
     * Enables the stop word functionality, ensuring that stop words are removed from consideration in a search.
     * The languages supported here are either every language (this is the default, see list of [Language]),
     * or those set by queryLanguages. See queryLanguages example below.
     */
    public object True : RemoveStopWords()

    /**
     * Disables stop word functionality, allowing stop words to be taken into account in a search.
     */
    public object False : RemoveStopWords()

    /**
     * A list of [Language] for which ignoring plurals should be enabled.
     * This list of [queryLanguages] will override any values that you may have set in [Settings.queryLanguages].
     */
    public data class QueryLanguages(val queryLanguages: List<Language>) : RemoveStopWords() {

        public constructor(vararg queryLanguage: Language) : this(queryLanguage.toList())
    }

    @Serializer(RemoveStopWords::class)
    public companion object : KSerializer<RemoveStopWords> {

        override fun serialize(encoder: Encoder, value: RemoveStopWords) {
            when (value) {
                is True -> Boolean.serializer().serialize(encoder, true)
                is False -> Boolean.serializer().serialize(encoder, false)
                is QueryLanguages -> Language.list.serialize(encoder, value.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): RemoveStopWords {
            return when (val element = decoder.asJsonInput()) {
                is JsonArray -> QueryLanguages(element.map {
                    JsonNonStrict.fromJson(Language, it)
                })
                is JsonLiteral -> if (element.boolean) True else False
                else -> throw Exception()
            }
        }
    }
}
