package com.algolia.search.serialize

import com.algolia.search.helper.and
import com.algolia.search.model.search.Point
import com.algolia.search.serialize.internal.regexPoint
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
