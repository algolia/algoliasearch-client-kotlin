package com.algolia.search.serialize

import com.algolia.search.model.search.HighlightResult
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list


internal object KSerializerHighlightResults : KSerializer<List<HighlightResult>> {

    private val serializer = HighlightResult.serializer()

    override val descriptor = serializer.list.descriptor

    override fun serialize(encoder: Encoder, obj: List<HighlightResult>) {
        val json = if (obj.size == 1) {
            Json.toJson(serializer, obj.first())
        } else {
            Json.toJson(serializer.list, obj)
        }

        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): List<HighlightResult> {
        return when (val json = decoder.asJsonInput()) {
            is JsonObject -> listOf(Json.fromJson(serializer, json))
            is JsonArray -> Json.fromJson(serializer.list, json)
            else -> throw Exception("Unknown type")
        }
    }
}