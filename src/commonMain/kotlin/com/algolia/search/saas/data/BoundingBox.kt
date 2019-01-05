package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


@Serializable(BoundingBox.Companion::class)
data class BoundingBox(
    val point1: Float,
    val point2: Float,
    val point3: Float,
    val point4: Float
) : Raw<List<Float>> {

    override val raw = listOf(point1, point2, point3, point4)

    @Serializer(BoundingBox::class)
    internal companion object : KSerializer<BoundingBox> {

        override fun serialize(encoder: Encoder, obj: BoundingBox) {
            encoder.asJsonOutput().encodeJson(jsonArray { obj.raw.forEach { +(it as Number) } })
        }

        override fun deserialize(decoder: Decoder): BoundingBox {
            val element = decoder.asJsonInput() as JsonArray

            val array = element.jsonArray

            return BoundingBox(
                array.getPrimitive(0).float,
                array.getPrimitive(1).float,
                array.getPrimitive(2).float,
                array.getPrimitive(3).float
            )
        }
    }
}