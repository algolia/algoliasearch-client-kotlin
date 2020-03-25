package com.algolia.search.model.search

import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.helper.toCursor
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * A cursor used to browse an index with [EndpointSearch.browse].
 */
@Serializable(Cursor.Companion::class)
data class Cursor(
    override val raw: String
) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<Cursor> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Cursor) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Cursor {
            return serializer.deserialize(decoder).toCursor()
        }
    }
}
