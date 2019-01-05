package com.algolia.search.saas.data

import com.algolia.search.saas.toCursor
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable
data class Cursor(
    override val raw: String
) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<Cursor> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: Cursor) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Cursor {
            return StringSerializer.deserialize(decoder).toCursor()
        }
    }
}