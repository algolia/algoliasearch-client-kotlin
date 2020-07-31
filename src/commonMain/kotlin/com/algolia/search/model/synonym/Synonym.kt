package com.algolia.search.model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyCorrections
import com.algolia.search.serialize.KeyInput
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeyReplacements
import com.algolia.search.serialize.KeySynonym
import com.algolia.search.serialize.KeySynonyms
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.KeyWord
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.serialize.regexPlaceholder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

@Serializable(Synonym.Companion::class)
public sealed class Synonym {

    /**
     * Must be unique. It can contain any character, and be of unlimited length.
     * With this method, you either create a new synonym or update an existing one.
     * In both cases, you must specify an [ObjectID]. When the [ObjectID] is not found in the index, it will create a new
     * synonym; otherwise, it will be an update.
     * Note that for some languages, this parameter is duplicated in the synonym object.
     */
    public abstract val objectID: ObjectID

    public data class OneWay(
        override val objectID: ObjectID,
        /**
         * Defines the synonym. A word or expression, used as the basis for the [synonyms].
         */
        val input: String,
        /**
         * A list of synonyms (up to 100).
         */
        val synonyms: List<String>
    ) : Synonym() {

        init {
            if (input.isBlank()) throw EmptyStringException("Input")
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
            require(synonyms.size <= limit) { "OneWay synonym have a maximum of $limit synonyms" }
        }

        public companion object {

            private const val limit = 100
        }
    }

    public data class MultiWay(
        override val objectID: ObjectID,
        /**
         * A list of synonyms (up to 20).
         */
        val synonyms: List<String>
    ) : Synonym() {

        init {
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
            require(synonyms.size <= limit) { "OneWay synonym have a maximum of $limit synonyms" }
        }

        public companion object {

            private const val limit = 20
        }
    }

    public data class AlternativeCorrections(
        override val objectID: ObjectID,
        /**
         * A single word, used as the basis for the [corrections].
         */
        val word: String,
        /**
         * An list of corrections of the [word].
         */
        val corrections: List<String>,
        /**
         * [SynonymType.Typo] indicating the number of alternative corrections.
         */
        val typo: SynonymType.Typo
    ) : Synonym() {

        init {
            if (word.isBlank()) throw EmptyStringException("Word")
            if (corrections.isEmpty()) throw EmptyListException("Corrections")
        }
    }

    public data class Placeholder(
        override val objectID: ObjectID,
        /**
         * A single word, used as the basis for the [replacements].
         */
        val placeholder: Token,
        /**
         * An list of replacements of the [placeholder].
         */
        val replacements: List<String>
    ) : Synonym() {

        init {
            if (replacements.isEmpty()) throw EmptyListException("Replacements")
        }

        /**
         * Creates the [Placeholder.placeholder] pattern for you.
         */
        public data class Token(val token: String) : Raw<String> {

            override val raw: String = "<$token>"

            init {
                if (token.isBlank()) throw EmptyStringException("Token")
            }
        }
    }

    public data class Other(
        override val objectID: ObjectID,
        val json: JsonObject
    ) : Synonym()

    @Serializer(Synonym::class)
    public companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, value: Synonym) {
            val json = when (value) {
                is MultiWay -> buildJsonObject {
                    put(KeyObjectID, value.objectID.raw)
                    put(KeyType, KeySynonym)
                    put(KeySynonyms, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.synonyms))
                }
                is OneWay -> buildJsonObject {
                    put(KeyObjectID, value.objectID.raw)
                    put(KeyType, KeyOneWaySynonym)
                    put(KeySynonyms, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.synonyms))
                    put(KeyInput, value.input)
                }
                is AlternativeCorrections -> buildJsonObject {
                    put(KeyObjectID, value.objectID.raw)
                    put(
                        KeyType,
                        when (value.typo) {
                            SynonymType.Typo.One -> KeyAlternativeCorrection1
                            SynonymType.Typo.Two -> KeyAlternativeCorrection2
                        }
                    )
                    put(KeyWord, value.word)
                    put(
                        KeyCorrections, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.corrections)
                    )
                }
                is Placeholder -> buildJsonObject {
                    put(KeyObjectID, value.objectID.raw)
                    put(KeyType, KeyPlaceholder)
                    put(KeyPlaceholder, value.placeholder.raw)
                    put(
                        KeyReplacements,
                        Json.encodeToJsonElement(ListSerializer(String.serializer()), value.replacements)
                    )
                }
                is Other -> value.json
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Synonym {
            val element = decoder.asJsonInput().jsonObject
            val objectID = element.getValue(KeyObjectID).jsonPrimitive.content.toObjectID()

            return if (element.containsKey(KeyType)) {
                when (element.getValue(KeyType).jsonPrimitive.content) {
                    KeySynonym -> MultiWay(
                        objectID,
                        element.getValue(KeySynonyms).jsonArray.map { it.jsonPrimitive.content }
                    )
                    KeyOneWaySynonym -> OneWay(
                        objectID,
                        element.getValue(KeyInput).jsonPrimitive.content,
                        element.getValue(KeySynonyms).jsonArray.map { it.jsonPrimitive.content }
                    )
                    KeyAlternativeCorrection1 -> AlternativeCorrections(
                        objectID,
                        element.getValue(KeyWord).jsonPrimitive.content,
                        element.getValue(KeyCorrections).jsonArray.map { it.jsonPrimitive.content },
                        SynonymType.Typo.One
                    )
                    KeyAlternativeCorrection2 -> AlternativeCorrections(
                        objectID,
                        element.getValue(KeyWord).jsonPrimitive.content,
                        element.getValue(KeyCorrections).jsonArray.map { it.jsonPrimitive.content },
                        SynonymType.Typo.Two
                    )
                    KeyPlaceholder -> {
                        val find = regexPlaceholder.find(element.getValue(KeyPlaceholder).jsonPrimitive.content)!!

                        Placeholder(
                            objectID,
                            Placeholder.Token(find.groupValues[1]),
                            element.getValue(KeyReplacements).jsonArray.map { it.jsonPrimitive.content }
                        )
                    }
                    else -> Other(objectID, element)
                }
            } else Other(objectID, element)
        }
    }
}
