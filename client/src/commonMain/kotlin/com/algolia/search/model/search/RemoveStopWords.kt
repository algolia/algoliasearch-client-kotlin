package com.algolia.search.model.search

import com.algolia.search.model.settings.Settings
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean

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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(RemoveStopWords::class)
    public companion object : KSerializer<RemoveStopWords> {

        override fun serialize(encoder: Encoder, value: RemoveStopWords) {
            when (value) {
                is True -> Boolean.serializer().serialize(encoder, true)
                is False -> Boolean.serializer().serialize(encoder, false)
                is QueryLanguages -> ListSerializer(Language).serialize(encoder, value.queryLanguages)
            }
        }

        override fun deserialize(decoder: Decoder): RemoveStopWords {
            return when (val element = decoder.asJsonInput()) {
                is JsonArray -> QueryLanguages(
                    element.map {
                        JsonNonStrict.decodeFromJsonElement(Language, it)
                    }
                )
                is JsonPrimitive -> if (element.boolean) True else False
                else -> throw Exception()
            }
        }
    }
}
