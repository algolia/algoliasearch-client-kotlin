package com.algolia.search.serialize

import com.algolia.search.model.ClientDate
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.content
import kotlinx.serialization.json.longOrNull

public object KSerializerClientDate : KSerializer<ClientDate> {

    private val serializer = String.serializer()

    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun serialize(encoder: Encoder, value: ClientDate) {
        serializer.serialize(encoder, value.raw)
    }

    override fun deserialize(decoder: Decoder): ClientDate {
        val input = decoder.asJsonInput()
        val long = input.longOrNull

        return if (long != null) ClientDate(long) else ClientDate(input.content)
    }
}
