package com.algolia.search.model.analytics

import com.algolia.search.helper.toABTestID
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * ID of an [ABTest].
 */
@Serializable(ABTestID.Companion::class)
public data class ABTestID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    public companion object : KSerializer<ABTestID> {

        private val serializer = Long.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: ABTestID) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): ABTestID {
            val long = serializer.deserialize(decoder)

            return long.toABTestID()
        }
    }
}
