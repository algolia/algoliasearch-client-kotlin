package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyMin
import com.algolia.search.saas.serialize.KeyStrict
import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.content


@Serializable(TypoTolerance.Companion::class)
sealed class TypoTolerance(override val raw: String) : Raw<String> {

    data class Boolean(val boolean: kotlin.Boolean) : TypoTolerance(boolean.toString())

    object Min : TypoTolerance(KeyMin)

    object Strict : TypoTolerance(KeyStrict)

    data class Unknown(override val raw: String) : TypoTolerance(raw)

    override fun toString(): String {
        return raw
    }

    @Serializer(TypoTolerance::class)
    internal companion object : KSerializer<TypoTolerance> {

        override fun serialize(encoder: Encoder, obj: TypoTolerance) {
            val element = when (obj) {
                is Boolean -> JsonPrimitive(obj.boolean)
                else -> JsonPrimitive(obj.raw)
            }

            encoder.asJsonOutput().encodeJson(element)
        }

        override fun deserialize(decoder: Decoder): TypoTolerance {
            val element = decoder.asJsonInput()

            return when {
                element.booleanOrNull != null -> Boolean(element.boolean)
                else -> {
                    when (val content = element.content) {
                        KeyMin -> Min
                        KeyStrict -> Strict
                        else -> Unknown(content)
                    }
                }
            }
        }
    }
}