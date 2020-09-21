package com.algolia.search.serialize

import com.algolia.search.model.ClientDate
import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull

public object KSerializerClientDate : KSerializer<ClientDate> {

    private val serializer = String.serializer()

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: ClientDate) {
        serializer.serialize(encoder, value.raw)
    }

    override fun deserialize(decoder: Decoder): ClientDate {
        val input = decoder.asJsonInput()
        val long = input.jsonPrimitive.longOrNull

        return if (long != null) ClientDate(long) else ClientDate(input.jsonPrimitive.content)
    }
}
