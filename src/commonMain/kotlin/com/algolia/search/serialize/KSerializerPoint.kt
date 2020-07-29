package com.algolia.search.serialize

import com.algolia.search.helper.and
import com.algolia.search.model.search.Point
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.builtins.serializer

public object KSerializerPoint : KSerializer<Point> {

    private val serializer = String.serializer()

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: Point) {
        serializer.serialize(encoder, "${value.latitude},${value.longitude}")
    }

    override fun deserialize(decoder: Decoder): Point {
        val values = regexPoint.find(serializer.deserialize(decoder))!!.groupValues

        return values[1].toFloat() and values[2].toFloat()
    }
}
