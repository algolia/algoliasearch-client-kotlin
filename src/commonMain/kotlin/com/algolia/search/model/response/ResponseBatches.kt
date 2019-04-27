package com.algolia.search.model.response

import com.algolia.search.client.ClientSearch
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.helper.toIndexName
import com.algolia.search.helper.toObjectID
import com.algolia.search.helper.toTaskID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.asJsonInput
import com.algolia.search.serialize.asJsonOutput
import kotlinx.serialization.*
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.long


@Serializable(ResponseBatches.Companion::class)
public data class ResponseBatches(
    /**
     * A list of [TaskIndex] to use with [ClientSearch.waitAll].
     */
    @SerialName(KeyTaskID) val tasks: List<TaskIndex>,
    /**
     * List of [ObjectID] affected by [EndpointMultipleIndex.multipleBatchObjects].
     */
    @SerialName(KeyObjectIDs) val objectIDsOrNull: List<ObjectID?>? = null
) {

    @Transient
    public val objectIDs: List<ObjectID?>
        get() = objectIDsOrNull!!

    @Serializer(ResponseBatches::class)
    companion object : KSerializer<ResponseBatches> {

        override fun serialize(encoder: Encoder, obj: ResponseBatches) {
            val json = json {
                KeyTaskID to json { obj.tasks.forEach { it.indexName.raw to it.taskID.raw } }
                KeyObjectIDs to obj.objectIDsOrNull?.let { jsonArray { it.forEach { +it?.raw } } }
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