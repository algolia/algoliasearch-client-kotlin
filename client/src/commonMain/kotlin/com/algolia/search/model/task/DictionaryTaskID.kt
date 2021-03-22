package com.algolia.search.model.task

import com.algolia.search.helper.toDictionaryTaskID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * This is a numeric value (up to 64bits) used to identify a [DictionaryTaskID].
 */
@Serializable(DictionaryTaskID.Companion::class)
public data class DictionaryTaskID(override val raw: Long) : AppTaskID {

    override fun toString(): String {
        return raw.toString()
    }

    public companion object : KSerializer<DictionaryTaskID> {
        private val serializer = Long.serializer()
        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: DictionaryTaskID) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): DictionaryTaskID {
            return serializer.deserialize(decoder).toDictionaryTaskID()
        }
    }
}
