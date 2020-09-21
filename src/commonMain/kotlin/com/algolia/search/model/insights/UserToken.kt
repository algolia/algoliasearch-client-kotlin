package com.algolia.search.model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toUserToken
import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.regexUserToken
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * A user identifier for analytics and security purposes.
 */
@Serializable(UserToken.Companion::class)
public data class UserToken(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("UserToken")
        if (raw.length > 64) throw IllegalArgumentException("UserToken length can't be superior to 64 characters.")
        if (!regexUserToken.matches(raw)) throw IllegalArgumentException("UserToken allows only characters of type [a-zA-Z0-9_-]")
    }

    public companion object : KSerializer<UserToken> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: UserToken) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): UserToken {
            return serializer.deserialize(decoder).toUserToken()
        }
    }
}
