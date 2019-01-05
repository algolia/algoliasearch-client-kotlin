package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAll
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.content
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull


@Serializable(AroundRadius.Companion::class)
sealed class AroundRadius(override val raw: String) : Raw<String> {

    object All : AroundRadius(KeyAll)

    data class InMeters(val int: kotlin.Int) : AroundRadius(int.toString())

    data class Unknown(override val raw: String) : AroundRadius(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(AroundRadius::class)
    internal companion object : KSerializer<AroundRadius> {

        override fun serialize(encoder: Encoder, obj: AroundRadius) {
            val element = when (obj) {
                is InMeters -> JsonPrimitive(obj.int)
                else -> JsonPrimitive(obj.raw)
            }

            encoder.asJsonOutput().encodeJson(element)
        }

        override fun deserialize(decoder: Decoder): AroundRadius {
            val element = decoder.asJsonInput()

            return when {
                element.intOrNull != null -> InMeters(element.int)
                else -> when (element.content) {
                    KeyAll -> All
                    else -> Unknown(element.content)
                }
            }
        }
    }
}