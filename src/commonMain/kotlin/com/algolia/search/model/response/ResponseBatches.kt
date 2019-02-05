package com.algolia.search.model.response

import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.TaskIndex
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


@Serializable(ResponseBatches.Companion::class)
data class ResponseBatches(
    @SerialName(KeyTaskID) val taskIDs: List<TaskIndex>,
    @Optional @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID?>? = null
) {

    @Serializer(ResponseBatches::class)
    companion object : KSerializer<ResponseBatches> {

        override fun serialize(encoder: Encoder, obj: ResponseBatches) {
            val json = json {
                KeyTaskID to json { obj.taskIDs.forEach { it.indexName.raw to it.taskID.raw } }
                KeyObjectIDs to obj.objectIDs?.let { jsonArray { it.forEach { +it?.raw } } }
            }

            encoder.asJsonOutput().encodeJson(json)
        }

        override fun deserialize(decoder: Decoder): ResponseBatches {
            val element = decoder.asJsonInput().jsonObject
            val taskIDs = element.getObject(KeyTaskID).map { (key, entry) ->
                TaskIndex(key.toIndexName(), entry.long.toTaskID())
            }
            val objectIDs = element.getArrayOrNull(KeyObjectIDs)?.map { it.contentOrNull?.toObjectID() }

            return ResponseBatches(taskIDs, objectIDs)
        }
    }
}