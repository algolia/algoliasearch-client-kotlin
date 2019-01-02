package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


@Serializable(BoundingBox.Companion::class)
data class BoundingBox(
    val point1: Float,
    val point2: Float,
    val point3: Float,
    val point4: Float
) : RawFloats {

    override val raw = listOf(point1, point2, point3, point4)

    @Serializer(BoundingBox::class)
    internal companion object : KSerializer<BoundingBox> {

        override fun serialize(output: Encoder, obj: BoundingBox) {
            val json = output as JSON.JsonOutput

            json.writeTree(jsonArray { obj.raw.forEach { +(it as Number) } })
        }

        override fun deserialize(input: Decoder): BoundingBox {
            val element = input.asJsonInput() as JsonArray

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