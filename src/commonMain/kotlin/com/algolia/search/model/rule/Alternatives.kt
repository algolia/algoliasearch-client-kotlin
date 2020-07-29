package com.algolia.search.model.rule

import com.algolia.search.serialize.asJsonInput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonLiteral

public sealed class Alternatives {

    public object True : Alternatives()

    public object False : Alternatives()

    @Serializer(Alternatives::class)
    public companion object : KSerializer<Alternatives> {

        override fun serialize(encoder: Encoder, value: Alternatives) {
            when (value) {
                is Alternatives.True -> Boolean.serializer().serialize(encoder, true)
                is Alternatives.False -> Boolean.serializer().serialize(encoder, false)
            }
        }

        override fun deserialize(decoder: Decoder): Alternatives {
            return when (val element = decoder.asJsonInput()) {
                is JsonLiteral -> if (element.boolean) Alternatives.True else Alternatives.False
                else -> throw Exception("Unsupported Type")
            }
        }
    }
}
