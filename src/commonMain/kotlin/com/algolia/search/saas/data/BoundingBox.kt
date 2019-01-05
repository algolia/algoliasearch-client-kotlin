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

        override val descriptor = FloatSerializer.list.descriptor

        override fun serialize(encoder: Encoder, obj: BoundingBox) {
            FloatSerializer.list.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): BoundingBox {
            val floats = FloatSerializer.list.deserialize(decoder)

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