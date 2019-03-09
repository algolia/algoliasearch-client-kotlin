package com.algolia.search.serialize

import com.algolia.search.helper.toAttribute
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.HighlightResult
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.HashMapClassDesc
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal object KSerializerHighlightResults : KSerializer<Map<Attribute, List<HighlightResult>>> {

    override val descriptor = HashMapClassDesc(Attribute.descriptor, HighlightResult.serializer().list.descriptor)

    private val serializer = HighlightResult.serializer()

    override fun serialize(encoder: Encoder, obj: Map<Attribute, List<HighlightResult>>) {
        val json = json {
            obj.forEach { (key, value) ->
                key.raw to if (value.size == 1) {
                    Json.plain.toJson(serializer, value.first())
                } else {
                    Json.plain.toJson(serializer.list, value)
                }
            }
        }

        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Map<Attribute, List<HighlightResult>> {
        val json = decoder.asJsonInput().jsonObject

        return json.map { (key, value) ->
            key.toAttribute() to when (value) {
                is JsonObject -> listOf(Json.plain.fromJson(serializer, value))
                is JsonArray -> Json.plain.fromJson(serializer.list, value)
                else -> throw Exception("Unknown type")
            }
        }.toMap()
    }

    fun fromJson(jsonObject: JsonObject): Map<Attribute, List<HighlightResult>> {
        return Json.plain.fromJson(KSerializerHighlightResults, jsonObject)
    }
}