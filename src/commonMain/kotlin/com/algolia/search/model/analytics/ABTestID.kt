package com.algolia.search.model.analytics

import com.algolia.search.model.Raw
import com.algolia.search.helper.toABTestID
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.LongSerializer


@Serializable(ABTestID.Companion::class)
data class ABTestID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    companion object : KSerializer<ABTestID> {

        private val serializer = LongSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: ABTestID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): ABTestID {
            val long = serializer.deserialize(decoder)

            return long.toABTestID()
        }
    }
}