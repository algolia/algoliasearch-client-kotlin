package com.algolia.search.saas.data

import com.algolia.search.saas.toUserID
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.LongSerializer


@Serializable(UserID.Companion::class)
data class UserID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    companion object : KSerializer<UserID> {

        private val serializer = LongSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: UserID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): UserID {
            val long = serializer.deserialize(decoder)

            return long.toUserID()
        }
    }
}