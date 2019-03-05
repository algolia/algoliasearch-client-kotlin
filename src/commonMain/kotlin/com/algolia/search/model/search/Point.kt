package com.algolia.search.model.search

import com.algolia.search.model.Raw
import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


@Serializable(Point.Companion::class)
data class Point(
    val latitude: Float,
    val longitude: Float
) : Raw<List<Float>> {

    override val raw = listOf(latitude, longitude)

    companion object : KSerializer<Point> {

        private val serializer = FloatSerializer.list

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Point) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Point {
            val floats = serializer.deserialize(decoder)

            return Point(floats[0], floats[1])
        }
    }
}