package com.algolia.search.model.task

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyPublished
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Current status of a [Task].
 */
@Serializable(TaskStatus.Companion::class)
public sealed class TaskStatus(override val raw: String) : Raw<String> {

    /**
     * The [Task] has been processed by the server.
     */
    public object Published : TaskStatus(KeyPublished)

    /**
     * The [Task] has not yet been processed by the server.
     */
    public object NotPublished : TaskStatus(KeyNotPublished)

    public data class Other(override val raw: String) : TaskStatus(raw)

    public companion object : KSerializer<TaskStatus> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: TaskStatus) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): TaskStatus {
            return when (val string = serializer.deserialize(decoder)) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Other(string)
            }
        }
    }
}
