package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer

/**
 * Search inside a rectangular area (in geo coordinates).
 * The rectangle is defined by two diagonally opposite points (hereafter [point1] and [point2]).
 */
@Serializable(BoundingBox.Companion::class)
public data class BoundingBox(
    val point1: Point,
    val point2: Point
) : Raw<List<Float>> {

    override val raw: List<Float> = listOf(point1.latitude, point1.longitude, point2.latitude, point2.longitude)

    public companion object : KSerializer<BoundingBox> {

        private val serializer = Float.serializer()

        override val descriptor: SerialDescriptor = serializer.list.descriptor

        override fun serialize(encoder: Encoder, value: BoundingBox) {
            serializer.list.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): BoundingBox {
            val floats = serializer.list.deserialize(decoder)

            return BoundingBox(
                floats[0] and floats[1],
                floats[2] and floats[3]
            )
        }
    }
}
