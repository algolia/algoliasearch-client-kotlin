package com.algolia.search.serialize

import com.algolia.search.model.search.Point
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.float
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

public object KSerializerGeoPoint : KSerializer<Point> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("point")

    override fun serialize(encoder: Encoder, value: Point) {
        val json = buildJsonObject {
            put(Key.Lat, value.latitude)
            put(Key.Lng, value.longitude)
        }

        encoder.asJsonOutput().encodeJsonElement(json)
    }

    override fun deserialize(decoder: Decoder): Point {
        val json = decoder.asJsonInput().jsonObject

        return Point(
            latitude = json.getValue(Key.Lat).jsonPrimitive.float,
            longitude = json.getValue(Key.Lng).jsonPrimitive.float
        )
    }
}
