package com.algolia.search.model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.regexPlaceholder
import kotlinx.serialization.ExperimentalSerializationApi
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

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(Synonym::class)
    public companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, value: Synonym) {
            val json = when (value) {
                is MultiWay -> buildJsonObject {
                    put(Key.ObjectID, value.objectID.raw)
                    put(Key.Type, Key.Synonym)
                    put(Key.Synonyms, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.synonyms))
                }
                is OneWay -> buildJsonObject {
                    put(Key.ObjectID, value.objectID.raw)
                    put(Key.Type, Key.OneWaySynonym)
                    put(Key.Synonyms, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.synonyms))
                    put(Key.Input, value.input)
                }
                is AlternativeCorrections -> buildJsonObject {
                    put(Key.ObjectID, value.objectID.raw)
                    put(
                        Key.Type,
                        when (value.typo) {
                            SynonymType.Typo.One -> Key.AlternativeCorrection1
                            SynonymType.Typo.Two -> Key.AlternativeCorrection2
                        }
                    )
                    put(Key.Word, value.word)
                    put(
                        Key.Corrections, Json.encodeToJsonElement(ListSerializer(String.serializer()), value.corrections)
                    )
                }
                is Placeholder -> buildJsonObject {
                    put(Key.ObjectID, value.objectID.raw)
                    put(Key.Type, Key.Placeholder)
                    put(Key.Placeholder, value.placeholder.raw)
                    put(
                        Key.Replacements,
                        Json.encodeToJsonElement(ListSerializer(String.serializer()), value.replacements)
                    )
                }
                is Other -> value.json
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): Synonym {
            val element = decoder.asJsonInput().jsonObject
            val objectID = element.getValue(Key.ObjectID).jsonPrimitive.content.toObjectID()

            return if (element.containsKey(Key.Type)) {
                when (element.getValue(Key.Type).jsonPrimitive.content) {
                    Key.Synonym -> MultiWay(
                        objectID,
                        element.getValue(Key.Synonyms).jsonArray.map { it.jsonPrimitive.content }
                    )
                    Key.OneWaySynonym -> OneWay(
                        objectID,
                        element.getValue(Key.Input).jsonPrimitive.content,
                        element.getValue(Key.Synonyms).jsonArray.map { it.jsonPrimitive.content }
                    )
                    Key.AlternativeCorrection1 -> AlternativeCorrections(
                        objectID,
                        element.getValue(Key.Word).jsonPrimitive.content,
                        element.getValue(Key.Corrections).jsonArray.map { it.jsonPrimitive.content },
                        SynonymType.Typo.One
                    )
                    Key.AlternativeCorrection2 -> AlternativeCorrections(
                        objectID,
                        element.getValue(Key.Word).jsonPrimitive.content,
                        element.getValue(Key.Corrections).jsonArray.map { it.jsonPrimitive.content },
                        SynonymType.Typo.Two
                    )
                    Key.Placeholder -> {
                        val find = regexPlaceholder.find(element.getValue(Key.Placeholder).jsonPrimitive.content)!!

                        Placeholder(
                            objectID,
                            Placeholder.Token(find.groupValues[1]),
                            element.getValue(Key.Replacements).jsonArray.map { it.jsonPrimitive.content }
                        )
                    }
                    else -> Other(objectID, element)
                }
            } else Other(objectID, element)
        }
    }
}
