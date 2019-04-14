package com.algolia.search.model.settings

import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.json.JsonLiteral


@Serializable(Distinct.Companion::class)
public data class Distinct(val count: Int) {

    init {
        if (count < 0) throw IllegalArgumentException("Distinct must be a positive integer")
    }

    companion object : KSerializer<Distinct> {

        override val descriptor = IntSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: Distinct) {
            encoder.asJsonOutput().encodeJson(JsonLiteral(obj.count))
        }

        override fun deserialize(decoder: Decoder): Distinct {
            val json = decoder.asJsonInput()
            val int = json.primitive.intOrNull

            if (int != null) {
                return Distinct(int)
            }
            return json.primitive.boolean.let { if (it) Distinct(1) else Distinct(0) }
        }
    }
}