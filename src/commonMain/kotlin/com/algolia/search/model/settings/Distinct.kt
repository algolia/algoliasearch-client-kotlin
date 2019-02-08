package com.algolia.search.model.settings

import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral


@Serializable(Distinct.Companion::class)
sealed class Distinct {

    object True : Distinct()

    object False : Distinct()

    data class Other(val count: Int) : Distinct() {

        init {
            if (count < 0) throw Exception("Distinct must be a positive integer")
        }
    }

    @Serializer(Distinct::class)
    companion object : KSerializer<Distinct> {

        override fun serialize(encoder: Encoder, obj: Distinct) {
            val json = when (obj) {
                is True -> JsonLiteral(true)
                is False -> JsonLiteral(false)
                is Other -> JsonLiteral(obj.count)
            }
            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): Distinct {
            val json = decoder.asJsonInput()
            val int = json.primitive.intOrNull

            if (int != null) {
                return when (int) {
                    0 -> False
                    1 -> True
                    else -> Other(int)
                }
            }
            return json.primitive.boolean.let { if (it) True else False }
        }
    }
}