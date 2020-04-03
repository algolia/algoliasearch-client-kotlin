package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.helper.toCursor
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

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

    companion object : KSerializer<Cursor> {

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Cursor) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): Cursor {
            return serializer.deserialize(decoder).toCursor()
        }
    }
}
