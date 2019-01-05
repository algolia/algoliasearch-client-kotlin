package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


@Serializable(Polygon.Companion::class)
data class Polygon(
    val point1: Point,
    val point2: Point,
    val point3: Point,
    private val points: List<Point>
) : Raw<List<Float>> {

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

    override val raw = listOf(
        *point1.raw.toTypedArray(),
        *point2.raw.toTypedArray(),
        *point3.raw.toTypedArray(),
        *points.flatMap { it.raw }.toTypedArray()
    )

    @Serializer(Polygon::class)
    internal companion object : KSerializer<Polygon> {

        override fun serialize(encoder: Encoder, obj: Polygon) {
            encoder.asJsonOutput().encodeJson(jsonArray { obj.raw.forEach { +(it as Number) } })
        }

        override fun deserialize(decoder: Decoder): Polygon {
            val element = decoder.asJsonInput() as JsonArray
            val array = element.jsonArray

            return Polygon(
                Point(array.getPrimitive(0).float, array.getPrimitive(1).float),
                Point(array.getPrimitive(2).float, array.getPrimitive(3).float),
                Point(array.getPrimitive(4).float, array.getPrimitive(5).float),
                (6 until array.size step 2).map {
                    Point(array.getPrimitive(it).float, array.getPrimitive(it + 1).float)
                }
            )
        }
    }
}