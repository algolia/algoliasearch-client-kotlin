package com.algolia.search.serialize

import com.algolia.search.model.search.Point
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray

public object KSerializerGeoPoints : KSerializer<List<Point>> {

    override val descriptor = SerialDescriptor("point")

    override fun serialize(encoder: Encoder, value: List<Point>) {
        val json = jsonArray {
            value.forEach { +Json.toJson(KSerializerGeoPoint, it) }
        }

        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): List<Point> {
        return when (val json = decoder.asJsonInput()) {
            is JsonArray -> Json.fromJson(KSerializerGeoPoint.list, json)
            else -> listOf(Json.fromJson(KSerializerGeoPoint, json))
        }
    }
}
