package com.algolia.search.model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import com.algolia.search.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


@Serializable(Synonym.Companion::class)
sealed class Synonym(open val objectID: ObjectID) {

    data class OneWay(
        override val objectID: ObjectID,
        val input: String,
        val synonyms: List<String>
    ) : Synonym(objectID) {

        init {
            if (input.isEmpty()) throw EmptyStringException("Input")
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
        }
    }

    data class MultiWay(
        override val objectID: ObjectID,
        val synonyms: List<String>
    ) : Synonym(objectID) {

        init {
            if (synonyms.isEmpty()) throw EmptyListException("Synonyms")
        }
    }

    data class AlternativeCorrections(
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

    data class Placeholder(
        override val objectID: ObjectID,
        val placeholder: Token,
        val replacements: List<String>
    ) : Synonym(objectID) {

        init {
            if (replacements.isEmpty()) throw EmptyListException("Replacements")
        }

        data class Token(val token: String) : Raw<String> {

            override val raw = "<$token>"

            init {
                if (token.isEmpty()) throw EmptyStringException("Token")
            }
        }
    }

    data class Other(
        val json: JsonObject,
        override val objectID: ObjectID
    ) : Synonym(objectID)

    @Serializer(Synonym::class)
    companion object : KSerializer<Synonym> {

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
            val objectID = element[KeyObjectID].content.toObjectID()

            return if (element.containsKey(KeyType)) {
                when (element[KeyType].content) {
                    KeySynonym -> MultiWay(
                        objectID,
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    KeyOneWaySynonym -> OneWay(
                        objectID,
                        element[KeyInput].content,
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    KeyAlternativeCorrection1 -> AlternativeCorrections(
                        objectID,
                        element[KeyWord].content,
                        element[KeyCorrections].jsonArray.map { it.content },
                        SynonymType.Typo.One
                    )
                    KeyAlternativeCorrection2 -> AlternativeCorrections(
                        objectID,
                        element[KeyWord].content,
                        element[KeyCorrections].jsonArray.map { it.content },
                        SynonymType.Typo.Two
                    )
                    KeyPlaceholder -> Placeholder(
                        objectID,
                        Placeholder.Token(element[KeyPlaceholder].content),
                        element[KeyReplacements].jsonArray.map { it.content }
                    )
                    else -> Other(element, objectID)
                }
            } else Other(element, objectID)
        }
    }
}