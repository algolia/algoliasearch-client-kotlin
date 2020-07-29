package com.algolia.search.model.search

import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer

/**
 * A set of geo-coordinates [latitude] and [longitude].
 */
@Serializable(Point.Companion::class)
public data class Point(
    val latitude: Float,
    val longitude: Float
) : Raw<List<Float>> {

    override val raw: List<Float> = listOf(latitude, longitude)

    public companion object : KSerializer<Point> {

        private val serializer = Float.serializer().list

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Point) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Point {
            val floats = serializer.deserialize(decoder)

            return Point(floats[0], floats[1])
        }
    }
}
