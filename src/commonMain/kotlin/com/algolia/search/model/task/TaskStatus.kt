package com.algolia.search.model.task

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyPublished
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(TaskStatus.Companion::class)
public sealed class TaskStatus(override val raw: String) : Raw<String> {

    public object Published : TaskStatus(KeyPublished)

    public object NotPublished : TaskStatus(KeyNotPublished)

    public data class Other(override val raw: String) : TaskStatus(raw)

    internal companion object : KSerializer<TaskStatus> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskStatus) {
            serializer.serialize(encoder, obj.raw)
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