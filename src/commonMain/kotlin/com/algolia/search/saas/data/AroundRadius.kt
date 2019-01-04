package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyAll
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


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

        override fun serialize(output: Encoder, obj: AroundRadius) {
            val element = when (obj) {
                is InMeters -> JsonPrimitive(obj.int)
                else -> JsonPrimitive(obj.raw)
            }

            output.asJsonOutput().writeTree(element)
        }

        override fun deserialize(input: Decoder): AroundRadius {
            val element = input.asJsonInput() as JsonLiteral

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