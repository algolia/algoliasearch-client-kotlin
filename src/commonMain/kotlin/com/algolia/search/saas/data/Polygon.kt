package com.algolia.search.saas.data

import kotlinx.serialization.*
import kotlinx.serialization.internal.FloatSerializer


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

    internal companion object : KSerializer<Polygon> {

        private val serializer = FloatSerializer.list

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Polygon) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Polygon {
            val floats = serializer.deserialize(decoder)

            return Polygon(
                Point(floats[0], floats[1]),
                Point(floats[2], floats[3]),
                Point(floats[4], floats[5]),
                (6 until floats.size step 2).map {
                    Point(floats[it], floats[it + 1])
                }
            )
        }
    }
}