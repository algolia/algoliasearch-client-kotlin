package com.algolia.search.model.search

import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.FloatSerializer
import kotlinx.serialization.list

/**
 * A set of geo-coordinates [latitude] and [longitude].
 */
@Serializable(Point.Companion::class)
public data class Point(
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
