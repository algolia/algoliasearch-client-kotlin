package client.data

import client.serialize.Deserializer
import client.serialize.RawFloatsSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement


data class Point(
    val latitude: Float,
    val longitude: Float
) : RawFloats {

    override val raw = listOf(latitude, longitude)

    internal companion object : RawFloatsSerializer<Point>, Deserializer<Point> {

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