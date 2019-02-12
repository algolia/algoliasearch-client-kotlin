package com.algolia.search.model.enums

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.content
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull


@Serializable(AroundRadius.Companion::class)
sealed class AroundRadius(override val raw: String) : Raw<String> {

    object All : AroundRadius(KeyAll)

    data class InMeters(val radius: Int) : AroundRadius(radius.toString())

    data class Other(override val raw: String) : AroundRadius(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(AroundRadius::class)
    companion object : KSerializer<AroundRadius> {

        override fun serialize(encoder: Encoder, obj: AroundRadius) {
            val element = when (obj) {
                is InMeters -> JsonPrimitive(obj.radius)
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
                    else -> Other(element.content)
                }
            }
        }
    }
}