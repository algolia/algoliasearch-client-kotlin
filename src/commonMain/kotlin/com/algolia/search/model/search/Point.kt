package com.algolia.search.model.search

import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

        private val serializer = ListSerializer(Float.serializer())

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
