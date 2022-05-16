package com.algolia.search.model.response

import com.algolia.search.client.ClientSearch
import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.helper.toIndexName
import com.algolia.search.helper.toObjectID
import com.algolia.search.helper.toTaskID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonArrayOrNull
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.put

@Serializable(ResponseBatches.Companion::class)
public data class ResponseBatches(
    /**
     * A list of [TaskIndex] to use with [ClientSearch.waitAll].
     */
    @SerialName(Key.TaskID) val tasks: List<TaskIndex>,
    /**
     * List of [ObjectID] affected by [EndpointMultipleIndex.multipleBatchObjects].
     */
    @SerialName(Key.ObjectIDs) val objectIDsOrNull: List<ObjectID?>? = null
) {

    public val objectIDs: List<ObjectID?>
        get() = objectIDsOrNull!!

    @OptIn(ExperimentalSerializationApi::class)
    @Serializer(ResponseBatches::class)
    public companion object : KSerializer<ResponseBatches> {

        override fun serialize(encoder: Encoder, value: ResponseBatches) {
            val json = buildJsonObject {
                put(
                    Key.TaskID,
                    buildJsonObject {
                        value.tasks.forEach {
                            put(it.indexName.raw, it.taskID.raw)
                        }
                    }
                )
                Key.ObjectIDs to value.objectIDsOrNull?.let { buildJsonArray { it.forEach { add(it?.raw) } } }
            }

            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): ResponseBatches {
            val element = decoder.asJsonInput().jsonObject
            val taskIDs = element.getValue(Key.TaskID).jsonObject.map { (key, entry) ->
                TaskIndex(key.toIndexName(), entry.jsonPrimitive.long.toTaskID())
            }
            val objectIDs =
                element[Key.ObjectIDs]?.jsonArrayOrNull?.map { it.jsonPrimitive.contentOrNull?.toObjectID() }

            return ResponseBatches(taskIDs, objectIDs)
        }
    }
}
