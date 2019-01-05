package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


@Serializable(Point.Companion::class)
data class Point(
    val latitude: Float,
    val longitude: Float
) : Raw<List<Float>> {

    override val raw = listOf(latitude, longitude)

    @Serializer(Point::class)
    internal companion object : KSerializer<Point> {

        override fun serialize(encoder: Encoder, obj: Point) {

            encoder.asJsonOutput().encodeJson(jsonArray { obj.raw.forEach { +(it as Number) } })
        }

        override fun deserialize(decoder: Decoder): Point {
            val element = decoder.asJsonInput() as JsonArray

            val array = element.jsonArray

            return Point(
                array.getPrimitive(0).float,
                array.getPrimitive(1).float
            )
        }
    }
}