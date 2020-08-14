package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.helper.toTaskID
import com.algolia.search.model.internal.Raw
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * This is a numeric value (up to 64bits) used to identify a [Task].
 * It can be used to perform a [EndpointAdvanced.waitTask] operation.
 */
@Serializable(TaskID.Companion::class)
public data class TaskID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    public companion object : KSerializer<TaskID> {

        private val serializer = Long.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: TaskID) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): TaskID {
            val long = serializer.deserialize(decoder)

            return long.toTaskID()
        }
    }
}
