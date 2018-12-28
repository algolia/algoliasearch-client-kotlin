package client.data

import client.serialize.Deserializer
import client.serialize.Serializer
import client.serialize.unwrap
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray


data class Polygon(
    val point1: Point,
    val point2: Point,
    val point3: Point,
    private val points: List<Point>
) {

    constructor(point1: Point, point2: Point, point3: Point, vararg points: Point) : this(
        point1,
        point2,
        point3,
        points.toList()
    )

    operator fun get(index: Int): Point {
        return when (index) {
            0 -> point1
            1 -> point2
            2 -> point3
            else -> points[index - 3]
        }
    }

    internal val floats = listOf(
        *point1.floats.toTypedArray(),
        *point2.floats.toTypedArray(),
        *point3.floats.toTypedArray(),
        *points.flatMap { it.floats }.toTypedArray()
    )

    internal companion object : Serializer<Polygon>, Deserializer<Polygon> {

        override fun serialize(input: Polygon?): JsonElement {
            return input.unwrap {
                jsonArray {
                    floats.forEach {
                        +(it as Number)
                    }
                }
            }
        }

        override fun deserialize(element: JsonElement): Polygon? {
            return when (element) {
                is JsonArray -> {
                    val array = element.jsonArray

                    Polygon(
                        Point(array.getPrimitive(0).float, array.getPrimitive(1).float),
                        Point(array.getPrimitive(2).float, array.getPrimitive(3).float),
                        Point(array.getPrimitive(4).float, array.getPrimitive(5).float),
                        (6 until array.size step 2).map {
                            Point(array.getPrimitive(it).float, array.getPrimitive(it + 1).float)
                        }
                    )
                }
                else -> null
            }
        }
    }
}