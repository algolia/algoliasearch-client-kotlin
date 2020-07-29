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
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json

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
                is MultiWay -> json {
                    KeyObjectID to value.objectID.raw
                    KeyType to KeySynonym
                    KeySynonyms to Json.toJson(String.serializer().list, value.synonyms)
                }
                is OneWay -> json {
                    KeyObjectID to value.objectID.raw
                    KeyType to KeyOneWaySynonym
                    KeySynonyms to Json.toJson(String.serializer().list, value.synonyms)
                    KeyInput to value.input
                }
                is AlternativeCorrections -> json {
                    KeyObjectID to value.objectID.raw
                    KeyType to when (value.typo) {
                        SynonymType.Typo.One -> KeyAlternativeCorrection1
                        SynonymType.Typo.Two -> KeyAlternativeCorrection2
                    }
                    KeyWord to value.word
                    KeyCorrections to Json.toJson(String.serializer().list, value.corrections)
                }
                is Placeholder -> json {
                    KeyObjectID to value.objectID.raw
                    KeyType to KeyPlaceholder
                    KeyPlaceholder to value.placeholder.raw
                    KeyReplacements to Json.toJson(String.serializer().list, value.replacements)
                }
                is Other -> value.json
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Synonym {
            val element = decoder.asJsonInput().jsonObject
            val objectID = element.getPrimitive(KeyObjectID).content.toObjectID()

            return if (element.containsKey(KeyType)) {
                when (element.getPrimitive(KeyType).content) {
                    KeySynonym -> MultiWay(
                        objectID,
                        element.getArray(KeySynonyms).map { it.content }
                    )
                    KeyOneWaySynonym -> OneWay(
                        objectID,
                        element.getPrimitive(KeyInput).content,
                        element.getArray(KeySynonyms).map { it.content }
                    )
                    KeyAlternativeCorrection1 -> AlternativeCorrections(
                        objectID,
                        element.getPrimitive(KeyWord).content,
                        element.getArray(KeyCorrections).map { it.content },
                        SynonymType.Typo.One
                    )
                    KeyAlternativeCorrection2 -> AlternativeCorrections(
                        objectID,
                        element.getPrimitive(KeyWord).content,
                        element.getArray(KeyCorrections).map { it.content },
                        SynonymType.Typo.Two
                    )
                    KeyPlaceholder -> {
                        val find = regexPlaceholder.find(element.getPrimitive(KeyPlaceholder).content)!!

                        Placeholder(
                            objectID,
                            Placeholder.Token(find.groupValues[1]),
                            element.getArray(KeyReplacements).map { it.content }
                        )
                    }
                    else -> Other(objectID, element)
                }
            } else Other(objectID, element)
        }
    }
}
