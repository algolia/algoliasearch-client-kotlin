package com.algolia.search.model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.helper.toUserToken
import com.algolia.search.model.Raw
import com.algolia.search.serialize.regexUserToken
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * A user identifier for analytics and security purposes.
 */
@Serializable(UserToken.Companion::class)
data class UserToken(override val raw: String) : Raw<String> {

    init {
        if (raw.isBlank()) throw EmptyStringException("UserToken")
        if (raw.length > 64) throw IllegalArgumentException("UserToken length can't be superior to 64 characters.")
        if (!regexUserToken.matches(raw)) throw IllegalArgumentException("UserToken allows only characters of type [a-zA-Z0-9_-]")
    }

    companion object : KSerializer<UserToken> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: UserToken) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): UserToken {
            return serializer.deserialize(decoder).toUserToken()
        }
    }
}
