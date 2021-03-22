package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.helper.toCursor
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A cursor used to browse an index with [EndpointSearch.browse].
 */
@Serializable(Cursor.Companion::class)
public data class Cursor(
    override val raw: String
) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<Cursor> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Cursor) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Cursor {
            return serializer.deserialize(decoder).toCursor()
        }
    }
}
