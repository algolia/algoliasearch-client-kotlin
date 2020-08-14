package com.algolia.search.model.multicluster

import com.algolia.search.helper.toUserID
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [UserID] in a multi-cluster setup.
 */
@Serializable(UserID.Companion::class)
public data class UserID(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    public companion object : KSerializer<UserID> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: UserID) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): UserID {
            val string = serializer.deserialize(decoder)

            return string.toUserID()
        }
    }
}
