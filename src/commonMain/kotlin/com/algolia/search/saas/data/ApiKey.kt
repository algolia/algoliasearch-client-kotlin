package com.algolia.search.saas.data

import com.algolia.search.saas.exception.EmptyStringException
import com.algolia.search.saas.toAPIKey
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringSerializer


@Serializable(APIKey.Companion::class)
data class APIKey(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) {
            throw EmptyStringException("APIKey")
        }
    }

    override fun toString(): String {
        return raw
    }

    @Serializer(APIKey::class)
    companion object: KSerializer<APIKey> {

        private val serializer = StringSerializer

        override fun serialize(encoder: Encoder, obj: APIKey) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): APIKey {
            return serializer.deserialize(decoder).toAPIKey()
        }
    }
}