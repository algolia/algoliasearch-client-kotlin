package com.algolia.search.serialize

import com.algolia.search.model.search.Point
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.json.json

public object KSerializerGeoPoint : KSerializer<Point> {

    override val descriptor: SerialDescriptor = SerialDescriptor("point")

    override fun serialize(encoder: Encoder, value: Point) {
        val json = json {
            KeyLat to value.latitude
            KeyLng to value.longitude
        }

        encoder.asJsonOutput().encodeJson(json)
    }

    override fun deserialize(decoder: Decoder): Point {
        val json = decoder.asJsonInput().jsonObject

        return Point(
            latitude = json.getPrimitive(KeyLat).float,
            longitude = json.getPrimitive(KeyLng).float
        )
    }
}
