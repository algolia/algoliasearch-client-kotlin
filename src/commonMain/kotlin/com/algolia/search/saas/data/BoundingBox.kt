package com.algolia.search.saas.data

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(BoundingBox.Companion::class)
data class BoundingBox(
    val point1: Point,
    val point2: Point
) : Raw<List<Float>> {

    override val raw = listOf(point1.latitude, point1.longitude, point2.latitude, point2.longitude)

    override fun toString(): String {
        return raw.joinToString(",") { it.toString() }
    }

    internal companion object : KSerializer<BoundingBox> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: BoundingBox) {
            serializer.serialize(encoder, obj.toString())
        }

        override fun deserialize(decoder: Decoder): BoundingBox {
            val floats = serializer.deserialize(decoder).split(",").map { it.toFloat() }

            return BoundingBox(
                Point(
                    floats[0],
                    floats[1]
                ),
                Point(
                    floats[2],
                    floats[3]
                )
            )
        }
    }
}