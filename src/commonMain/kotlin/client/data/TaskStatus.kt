package client.data

import client.serialize.KeyNotPublished
import client.serialize.KeyPublished
import client.serialize.readAsTree
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
            val element = input.readAsTree() as JsonLiteral

            return when (val content = element.content) {
                KeyPublished -> Published
                KeyNotPublished -> NotPublished
                else -> Unknown(content)
            }
        }
    }
}