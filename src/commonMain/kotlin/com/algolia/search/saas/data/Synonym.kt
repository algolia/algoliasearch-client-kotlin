package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.*


@Serializable(Synonym.Companion::class)
sealed class Synonym {

    enum class Typo {
        One,
        Two
    }

    data class MultiWay(
        val synonyms: List<String>
    ) : Synonym()

    data class OneWay(
        val input: String,
        val synonyms: List<String>
    ) : Synonym()

    data class AlternativeCorrections(
        val word: String,
        val corrections: List<String>,
        val typo: Typo
    ) : Synonym()

    data class Placeholder(
        val placeholder: String,
        val replacements: List<String>
    ) : Synonym()

    data class Other(
        val json: JsonObject
    ) : Synonym()

    internal fun addObjectId(objectID: ObjectID): JsonObject {
        val jsonObject = Json.plain.toJson(this, Synonym).jsonObject
        val map = jsonObject.toMutableMap().apply {
            put(KeyObjectID, JsonLiteral(objectID.raw))
        }
        return JsonObject(map)
    }

    @Serializer(Synonym::class)
    companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, obj: Synonym) {
            val json = when (obj) {
                is MultiWay -> json {
                    KeyType to KeySynonym
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                }
                is OneWay -> json {
                    KeyType to KeyOneWaySynonym
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                    KeyInput to obj.input
                }
                is AlternativeCorrections -> json {
                    KeyType to when (obj.typo) {
                        Typo.One -> KeyAlternativeCorrection1
                        Typo.Two -> KeyAlternativeCorrection2
                    }
                    KeyWord to obj.word
                    KeyCorrections to Json.plain.toJson(obj.corrections, StringSerializer.list)
                }
                is Placeholder -> json {
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

            return if (element.containsKey(KeyType)) {
                when (element[KeyType].content) {
                    KeySynonym -> MultiWay(
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    KeyOneWaySynonym -> OneWay(
                        element[KeyInput].content,
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    KeyAlternativeCorrection1 -> AlternativeCorrections(
                        element[KeyWord].content,
                        element[KeyCorrections].jsonArray.map { it.content },
                        Typo.One
                    )
                    KeyAlternativeCorrection2 -> AlternativeCorrections(
                        element[KeyWord].content,
                        element[KeyCorrections].jsonArray.map { it.content },
                        Typo.Two
                    )
                    KeyPlaceholder -> Placeholder(
                        element[KeyPlaceholder].content,
                        element[KeyReplacements].jsonArray.map { it.content }
                    )
                    else -> Other(element)
                }
            } else Other(element)
        }
    }
}