package com.algolia.search.model.search

import com.algolia.search.helper.and
import com.algolia.search.model.Raw
import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


@Serializable(BoundingBox.Companion::class)
data class BoundingBox(
    val point1: Point,
    val point2: Point
) : Raw<List<Float>> {

    override val raw = listOf(point1.latitude, point1.longitude, point2.latitude, point2.longitude)

    companion object : KSerializer<BoundingBox> {

        private val serializer = FloatSerializer

        override val descriptor = serializer.list.descriptor

        override fun serialize(encoder: Encoder, obj: BoundingBox) {
            serializer.list.serialize(encoder, obj.raw)
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