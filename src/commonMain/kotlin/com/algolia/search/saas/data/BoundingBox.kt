package com.algolia.search.saas.data

import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


@Serializable(BoundingBox.Companion::class)
data class BoundingBox(
    val point1: Point,
    val point2: Point
) : Raw<List<Float>> {

    override val raw = listOf(point1.latitude, point1.longitude, point2.latitude, point2.longitude)

    internal companion object : KSerializer<BoundingBox> {

        private val serializer = FloatSerializer

        override val descriptor = serializer.list.descriptor

        override fun serialize(encoder: Encoder, obj: BoundingBox) {
            serializer.list.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): BoundingBox {
            val floats = serializer.list.deserialize(decoder)

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