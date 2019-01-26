package com.algolia.search.model.indexing

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.TaskIndex
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import com.algolia.search.toIndexName
import com.algolia.search.toObjectID
import com.algolia.search.toTaskID
import kotlinx.serialization.*
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.long


@Serializable(TaskBatchOperations.Companion::class)
data class TaskBatchOperations(
    @SerialName(KeyTaskID) val taskIDs: List<TaskIndex>,
    @Optional val objectIDs: List<ObjectID?>? = null
) {

    @Serializer(TaskBatchOperations::class)
    companion object : KSerializer<TaskBatchOperations> {

        override fun serialize(encoder: Encoder, obj: TaskBatchOperations) {
            val json = json {
                KeyTaskID to json { obj.taskIDs.forEach { it.indexName.raw to it.taskID.raw } }
                KeyObjectIDs to obj.objectIDs?.let { jsonArray { it.forEach { +it?.raw } } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): TaskBatchOperations {
            val element = decoder.asJsonInput().jsonObject
            val taskIDs = element.getObject(KeyTaskID).map { (key, entry) ->
                TaskIndex(key.toIndexName(), entry.long.toTaskID())
            }
            val objectIDs = element.getArrayOrNull(KeyObjectIDs)?.map { it.contentOrNull?.toObjectID() }

            return TaskBatchOperations(taskIDs, objectIDs)
        }
    }
}