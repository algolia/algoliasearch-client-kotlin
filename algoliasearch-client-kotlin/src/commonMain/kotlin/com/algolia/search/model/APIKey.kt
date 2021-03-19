package com.algolia.search.model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toAPIKey
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [APIKey] can't be a blank or empty string.
 */
@Serializable(APIKey.Companion::class)
public data class APIKey(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("APIKey")
    }

    override fun toString(): String {
        return raw
    }

    @Serializer(APIKey::class)
    @OptIn(ExperimentalSerializationApi::class)
    public companion object : KSerializer<APIKey> {

        private val serializer = String.serializer()

        override fun serialize(encoder: Encoder, value: APIKey) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): APIKey {
            return serializer.deserialize(decoder).toAPIKey()
        }
    }
}
