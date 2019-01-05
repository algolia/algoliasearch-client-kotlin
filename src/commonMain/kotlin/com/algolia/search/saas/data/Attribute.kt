package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toAttribute
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(Attribute.Companion::class)
data class Attribute(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    @Serializer(Attribute::class)
    internal companion object : KSerializer<Attribute> {

        override fun serialize(encoder: Encoder, obj: Attribute) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): Attribute {
            val element = decoder.asJsonInput() as JsonLiteral

            return element.content.toAttribute()
        }
    }
}