package com.algolia.search.model

import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toQueryID
import com.algolia.search.model.internal.Raw
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.search.Query
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Unique identifier for a [Query]. Returned by [ResponseSearch] and used by [EndpointInsightsUser]
 */
@Serializable(QueryID.Companion::class)
public data class QueryID(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("QueryID")
    }

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<QueryID> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: QueryID) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): QueryID {
            return serializer.deserialize(decoder).toQueryID()
        }
    }
}
