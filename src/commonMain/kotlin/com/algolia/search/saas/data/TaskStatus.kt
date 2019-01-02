package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.KeyNotPublished
import com.algolia.search.saas.serialize.KeyPublished
import com.algolia.search.saas.serialize.asJsonInput
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(TaskStatus.Companion::class)
sealed class TaskStatus(override val raw: String) : RawString {

    object Published : TaskStatus(KeyPublished)

    object NotPublished : TaskStatus(KeyNotPublished)

    data class Unknown(override val raw: String) : TaskStatus(raw)

    @Serializer(TaskStatus::class)
    companion object : KSerializer<TaskStatus> {

        override fun serialize(output: Encoder, obj: TaskStatus) {
            val json = output as JSON.JsonOutput

            json.writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): TaskStatus {
            val element = input.asJsonInput() as JsonLiteral

            return when (val content = element.content) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Unknown(content)
            }
        }
    }
}