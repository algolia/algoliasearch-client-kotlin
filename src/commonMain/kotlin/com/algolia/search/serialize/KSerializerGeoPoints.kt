package com.algolia.search.serialize

import com.algolia.search.model.search.Point
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.list


internal object KSerializerGeoPoints : KSerializer<List<Point>> {

    override val descriptor = SerialClassDescImpl("point")

    override fun serialize(encoder: Encoder, obj: List<Point>) {
        val json = jsonArray {
            obj.forEach { +Json.toJson(KSerializerGeoPoint, it) }
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