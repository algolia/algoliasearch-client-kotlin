package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.toCursor
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable
data class Cursor(
    override val raw: String
) : RawString {

    override fun toString(): String {
        return raw
    }

    @Serializer(Cursor::class)
    internal companion object : KSerializer<Cursor> {

        override fun serialize(output: Encoder, obj: Cursor) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): Cursor {
            val element = input.asJsonInput() as JsonLiteral

            return element.content.toCursor()
        }
    }
}