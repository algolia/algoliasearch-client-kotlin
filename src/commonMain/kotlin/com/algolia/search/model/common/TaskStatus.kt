package com.algolia.search.model.common

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyPublished
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(TaskStatus.Companion::class)
sealed class TaskStatus(override val raw: String) : Raw<String> {

    object Published : TaskStatus(KeyPublished)

    object NotPublished : TaskStatus(KeyNotPublished)

    data class Other(override val raw: String) : TaskStatus(raw)

    companion object : KSerializer<TaskStatus> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskStatus) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskStatus {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Other(string)
            }
        }
    }
}