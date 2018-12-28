package client.data

import client.serialize.Serializer
import client.serialize.unwrap
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray


data class Point(
    val latitude: Float,
    val longitude: Float
) {

    internal val floats = listOf(latitude, longitude)

    internal companion object : Serializer<Point> {

        override fun serialize(input: Point?): JsonElement {
            return input.unwrap {
                jsonArray {
                    +(latitude as Number)
                    +(longitude as Number)
                }
            }
        }

        override fun deserialize(element: JsonElement): Point? {
            return when (element) {
                is JsonArray -> {
                    val array = element.jsonArray

                    Point(
                        array.getPrimitive(0).float,
                        array.getPrimitive(1).float
                    )
                }
                else -> null
            }
        }
    }
}