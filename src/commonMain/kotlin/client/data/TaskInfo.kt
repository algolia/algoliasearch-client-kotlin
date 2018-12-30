package client.data

import client.serialize.Deserializer
import client.serialize.KeyStatus
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject


data class TaskInfo(
    val status: TaskStatus
) {

    internal companion object : Deserializer<TaskInfo> {

        override fun deserialize(element: JsonElement): TaskInfo? {
            var status: TaskStatus? = null

            when (element) {
                is JsonObject -> {
                    element.forEach { (key, element) ->
                        when (key) {
                            KeyStatus -> status = TaskStatus.deserialize(element)!!
                        }
                    }
                }
                else -> return null
            }
            return status?.let { TaskInfo(it) }
        }
    }
}