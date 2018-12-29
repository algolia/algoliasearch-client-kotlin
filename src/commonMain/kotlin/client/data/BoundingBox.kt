package client.data

import client.serialize.Deserializer
import client.serialize.RawFloatsSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement


data class BoundingBox(
    val point1: Float,
    val point2: Float,
    val point3: Float,
    val point4: Float
) : RawFloats {

    override val raw = listOf(point1, point2, point3, point4)

    internal companion object : RawFloatsSerializer<BoundingBox>, Deserializer<BoundingBox> {

        override fun deserialize(element: JsonElement): BoundingBox? {
            return when (element) {
                is JsonArray -> {
                    val array = element.jsonArray

                    BoundingBox(
                        array.getPrimitive(0).float,
                        array.getPrimitive(1).float,
                        array.getPrimitive(2).float,
                        array.getPrimitive(3).float
                    )
                }
                else -> null
            }
        }
    }
}