package com.algolia.search.model.task

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.helper.toTaskID
import com.algolia.search.model.Raw
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.LongSerializer


/**
 * This is a numeric value (up to 64bits) used to identify a [Task].
 * It can be used to perform a [EndpointAdvanced.waitTask] operation.
 */
@Serializable(TaskID.Companion::class)
public data class TaskID(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    companion object : KSerializer<TaskID> {

        private val serializer = LongSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskID) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskID {
            val long = serializer.deserialize(decoder)

            return long.toTaskID()
        }
    }
}