package client.data

import client.serialize.Deserializer
import client.serialize.KeyNotPublished
import client.serialize.KeyPublished
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class TaskStatus(override val raw: String) : RawString {

    object Published : TaskStatus(KeyPublished)

    object NotPublished : TaskStatus(KeyNotPublished)

    data class Unknown(override val raw: String) : TaskStatus(raw)

    companion object : Deserializer<TaskStatus> {

        override fun deserialize(element: JsonElement): TaskStatus? {
            return when (element) {
                is JsonPrimitive -> {
                    when (val content = element.contentOrNull) {
                        KeyPublished -> Published
                        KeyNotPublished -> NotPublished
                        null -> null
                        else -> Unknown(content)
                    }
                }
                else -> null
            }
        }
    }
}