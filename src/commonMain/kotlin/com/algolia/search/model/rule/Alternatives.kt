package com.algolia.search.model.rule

import com.algolia.search.serialize.internal.asJsonInput
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean

public sealed class Alternatives {

    public object True : Alternatives()

    public object False : Alternatives()

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(Alternatives::class)
    public companion object : KSerializer<Alternatives> {

        override fun serialize(encoder: Encoder, value: Alternatives) {
            when (value) {
                is True -> Boolean.serializer().serialize(encoder, true)
                is False -> Boolean.serializer().serialize(encoder, false)
            }
        }

        override fun deserialize(decoder: Decoder): Alternatives {
            return when (val element = decoder.asJsonInput()) {
                is JsonPrimitive -> if (element.boolean) True else False
                else -> throw Exception("Unsupported Type")
            }
        }
    }
}
