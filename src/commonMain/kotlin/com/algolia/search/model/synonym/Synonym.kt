package com.algolia.search.model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


@Serializable(Synonym.Companion::class)
public sealed class Synonym(open val objectID: ObjectID) {

    public data class OneWay(
        override val objectID: ObjectID,
        val input: String,
        val synonyms: List<String>
    ) : Synonym(objectID) {

        init {
            if (input.isEmpty()) throw EmptyStringException("Input")
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
        }
    }

    public data class MultiWay(
        override val objectID: ObjectID,
        val synonyms: List<String>
    ) : Synonym(objectID) {

        init {
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
        }
    }

    public data class AlternativeCorrections(
        override val objectID: ObjectID,
        val word: String,
        val corrections: List<String>,
        val typo: SynonymType.Typo
    ) : Synonym(objectID) {

        init {
            if (word.isEmpty()) throw EmptyStringException("Word")
            if (corrections.isEmpty()) throw EmptyListException("Corrections")
        }

    }

    public data class Placeholder(
        override val objectID: ObjectID,
        val placeholder: Token,
        val replacements: List<String>
    ) : Synonym(objectID) {

        init {
            if (replacements.isEmpty()) throw EmptyListException("Replacements")
        }

        public data class Token(val token: String) : Raw<String> {

            override val raw = "<$token>"

            init {
                if (token.isEmpty()) throw EmptyStringException("Token")
            }
        }
    }

    public data class Other(
        override val objectID: ObjectID,
        val json: JsonObject
    ) : Synonym(objectID)

    @Serializer(Synonym::class)
    internal companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, obj: Synonym) {
            val json = when (obj) {
                is MultiWay -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeySynonym
                    KeySynonyms to Json.plain.toJson(StringSerializer.list, obj.synonyms)
                }
                is OneWay -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyOneWaySynonym
                    KeySynonyms to Json.plain.toJson(StringSerializer.list, obj.synonyms)
                    KeyInput to obj.input
                }
                is AlternativeCorrections -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to when (obj.typo) {
                        SynonymType.Typo.One -> KeyAlternativeCorrection1
                        SynonymType.Typo.Two -> KeyAlternativeCorrection2
                    }
                    KeyWord to obj.word
                    KeyCorrections to Json.plain.toJson(StringSerializer.list, obj.corrections)
                }
                is Placeholder -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyPlaceholder
                    KeyPlaceholder to obj.placeholder.raw
                    KeyReplacements to Json.plain.toJson(StringSerializer.list, obj.replacements)
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