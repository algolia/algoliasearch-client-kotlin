package com.algolia.search.saas.data

import com.algolia.search.saas.serialize.asJsonInput
import com.algolia.search.saas.serialize.asJsonOutput
import com.algolia.search.saas.toTaskId
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive


@Serializable(TaskId.Companion::class)
data class TaskId(override val raw: Long) : Raw<Long> {

    override fun toString(): String {
        return raw.toString()
    }

    @Serializer(TaskId::class)
    companion object : KSerializer<TaskId> {

        override fun serialize(output: Encoder, obj: TaskId) {
            output.asJsonOutput().writeTree(JsonPrimitive(obj.raw))
        }

        override fun deserialize(input: Decoder): TaskId {
            val element = input.asJsonInput() as JsonLiteral

            return element.long.toTaskId()
        }
    }
}