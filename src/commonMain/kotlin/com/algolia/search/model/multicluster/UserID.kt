package com.algolia.search.model.multicluster

import com.algolia.search.helper.toUserID
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer

/**
 * [UserID] in a multi-cluster setup.
 */
@Serializable(UserID.Companion::class)
public data class UserID(override val raw: String) : Raw<String> {

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<UserID> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: UserID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): UserID {
            val string = serializer.deserialize(decoder)

            return string.toUserID()
        }
    }
}
