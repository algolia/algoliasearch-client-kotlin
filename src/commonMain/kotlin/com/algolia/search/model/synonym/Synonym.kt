package com.algolia.search.model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
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
    abstract val objectID: ObjectID

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
            if (synonyms.size > 100) throw IllegalArgumentException("OneWay synonym have a maximum of 100 synonyms")
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
            if (synonyms.size > 20) throw IllegalArgumentException("OneWay synonym have a maximum of 100 synonyms")
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

            override val raw = "<$token>"

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
    companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, obj: Synonym) {
            val json = when (obj) {
                is MultiWay -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeySynonym
                    KeySynonyms to Json.toJson(StringSerializer.list, obj.synonyms)
                }
                is OneWay -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyOneWaySynonym
                    KeySynonyms to Json.toJson(StringSerializer.list, obj.synonyms)
                    KeyInput to obj.input
                }
                is AlternativeCorrections -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to when (obj.typo) {
                        SynonymType.Typo.One -> KeyAlternativeCorrection1
                        SynonymType.Typo.Two -> KeyAlternativeCorrection2
                    }
                    KeyWord to obj.word
                    KeyCorrections to Json.toJson(StringSerializer.list, obj.corrections)
                }
                is Placeholder -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyPlaceholder
                    KeyPlaceholder to obj.placeholder.raw
                    KeyReplacements to Json.toJson(StringSerializer.list, obj.replacements)
                }
                is Other -> obj.json
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