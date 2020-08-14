package com.algolia.search.serialize

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.buildJsonArray

public object KSerializerGeoPoints : KSerializer<List<Point>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("point")

    override fun serialize(encoder: Encoder, value: List<Point>) {
        val json = buildJsonArray {
            value.forEach { add(Json.encodeToJsonElement(KSerializerGeoPoint, it)) }
        }

        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): List<Point> {
        return when (val json = decoder.asJsonInput()) {
            is JsonArray -> Json.decodeFromJsonElement(ListSerializer(KSerializerGeoPoint), json)
            else -> listOf(Json.decodeFromJsonElement(KSerializerGeoPoint, json))
        }
    }
}
