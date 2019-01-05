package com.algolia.search.saas.data

import com.algolia.search.saas.toTaskId
import kotlinx.serialization.*
import kotlinx.serialization.internal.LongSerializer


@Serializable(TaskId.Companion::class)
data class TaskId(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    @Serializer(TaskId::class)
    companion object : KSerializer<TaskId> {

        override fun serialize(encoder: Encoder, obj: TaskId) {
            LongSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskId {
            val long = LongSerializer.deserialize(decoder)

            return long.toTaskId()
        }
    }
}