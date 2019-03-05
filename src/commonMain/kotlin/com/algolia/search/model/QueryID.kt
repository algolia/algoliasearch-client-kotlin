package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toQueryID
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(QueryID.Companion::class)
public data class QueryID(override val raw: String) : Raw<String> {

    init {
        if (raw.isEmpty()) throw EmptyStringException("QueryID")
    }

    override fun toString(): String {
        return raw
    }

    internal companion object : KSerializer<QueryID> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: QueryID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): QueryID {
            return serializer.deserialize(decoder).toQueryID()
        }
    }
}