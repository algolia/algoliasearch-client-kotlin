package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyNotPublished
import com.algolia.search.saas.serialize.KeyPublished
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Serializable(TaskStatus.Companion::class)
sealed class TaskStatus(override val raw: String) : Raw<String> {

    object Published : TaskStatus(KeyPublished)

    object NotPublished : TaskStatus(KeyNotPublished)

    data class Unknown(override val raw: String) : TaskStatus(raw)

    companion object : KSerializer<TaskStatus> {

        override val descriptor = StringSerializer.descriptor

        override fun serialize(encoder: Encoder, obj: TaskStatus) {
            StringSerializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): TaskStatus {
            val string = StringSerializer.deserialize(decoder)

            return when (string) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Unknown(string)
            }
        }
    }
}