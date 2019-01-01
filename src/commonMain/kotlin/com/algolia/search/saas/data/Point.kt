package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.readAsTree
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray


@Serializable(Point.Companion::class)
data class Point(
    val latitude: Float,
    val longitude: Float
) : RawFloats {

    override val raw = listOf(latitude, longitude)

    @Serializer(Point::class)
    internal companion object : KSerializer<Point> {

        override fun serialize(output: Encoder, obj: Point) {
            val json = output as JSON.JsonOutput

            json.writeTree(jsonArray { obj.raw.forEach { +(it as Number) } })
        }

        override fun deserialize(input: Decoder): Point {
            val element = input.readAsTree() as JsonArray

            val array = element.jsonArray

            return Point(
                array.getPrimitive(0).float,
                array.getPrimitive(1).float
            )
        }
    }
}