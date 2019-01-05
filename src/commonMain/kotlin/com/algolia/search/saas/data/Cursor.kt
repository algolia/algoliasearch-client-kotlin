package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toCursor
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable
data class Cursor(
    override val raw: String
) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    @Serializer(Cursor::class)
    internal companion object : KSerializer<Cursor> {

        override fun serialize(encoder: Encoder, obj: Cursor) {
            encoder.asJsonOutput().encodeJson(JsonPrimitive(obj.raw))
        }

        override fun deserialize(decoder: Decoder): Cursor {
            val element = decoder.asJsonInput() as JsonLiteral

            return element.content.toCursor()
        }
    }
}