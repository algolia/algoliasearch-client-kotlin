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
sealed class Synonym {

    data class MultiWay(val objectID: ObjectID, val synonyms: List<String>) : Synonym() {

        constructor(objectID: ObjectID, vararg synonyms: String) : this(objectID, synonyms.toList())
    }

    data class OneWay(val objectID: ObjectID, val input: String, val synonyms: List<String>) : Synonym() {

        constructor(objectID: ObjectID, input: String, vararg synonyms: String) : this(
            objectID,
            input,
            synonyms.toList()
        )
    }

    data class Other(val json: JsonObject) : Synonym()

    @Serializer(Synonym::class)
    internal companion object : KSerializer<Synonym> {

        override fun serialize(encoder: Encoder, obj: Synonym) {
            val json = when (obj) {
                is MultiWay -> json {
                    KeyType to KeySynonym
                    KeyObjectId to obj.objectID.raw
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                }
                is OneWay -> json {
                    KeyType to KeyOneWaySynonym
                    KeyObjectId to obj.objectID.raw
                    KeySynonyms to Json.plain.toJson(obj.synonyms, StringSerializer.list)
                    KeyInput to obj.input
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
                        element[KeyObjectId].content.toObjectID(),
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    KeyOneWaySynonym -> OneWay(
                        element[KeyObjectId].content.toObjectID(),
                        element[KeyInput].content,
                        element[KeySynonyms].jsonArray.map { it.content }
                    )
                    else -> Other(element)
                }
            } else Other(element)
        }
    }
}