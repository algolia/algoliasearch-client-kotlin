package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toObjectID
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.content
import kotlinx.serialization.json.json


@Serializable(Synonym.Companion::class)
sealed class Synonym(open val objectID: ObjectID) {

    enum class Typo {
        One,
        Two
    }

    data class MultiWay(
        override val objectID: ObjectID,
        val synonyms: List<String>
    ) : Synonym(objectID)

    data class OneWay(
        override val objectID: ObjectID,
        val input: String,
        val synonyms: List<String>
    ) : Synonym(objectID)

    data class AlternativeCorrections(
        override val objectID: ObjectID,
        val word: String,
        val corrections: List<String>,
        val typo: Typo
    ) : Synonym(objectID)

    data class Placeholder(
        override val objectID: ObjectID,
        val placeholder: String,
        val replacements: List<String>
    ) : Synonym(objectID)

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
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                }
                is OneWay -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyOneWaySynonym
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                    KeyInput to obj.input
                }
                is AlternativeCorrections -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to when (obj.typo) {
                        Typo.One -> KeyAlternativeCorrection1
                        Typo.Two -> KeyAlternativeCorrection2
                    }
                    KeyWord to obj.word
                    KeyCorrections to Json.plain.toJson(obj.corrections, StringSerializer.list)
                }
                is Placeholder -> json {
                    KeyObjectID to obj.objectID.raw
                    KeyType to KeyPlaceholder
                    KeyPlaceholder to obj.placeholder
                    KeyReplacements to Json.plain.toJson(obj.replacements, StringSerializer.list)
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
                        Typo.One
                    )
                    KeyAlternativeCorrection2 -> AlternativeCorrections(
                        objectID,
                        element[KeyWord].content,
                        element[KeyCorrections].jsonArray.map { it.content },
                        Typo.Two
                    )
                    KeyPlaceholder -> Placeholder(
                        objectID,
                        element[KeyPlaceholder].content,
                        element[KeyReplacements].jsonArray.map { it.content }
                    )
                    else -> Other(element, objectID)
                }
            } else Other(element, objectID)
        }
    }
}