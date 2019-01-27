package com.algolia.search.model.multipleindex

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.TaskIndex
import com.algolia.search.model.search.SearchResponse
import com.algolia.search.serialize.*
import com.algolia.search.toIndexName
import com.algolia.search.toObjectID
import com.algolia.search.toTaskID
import kotlinx.serialization.*
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.long


sealed class MultipleIndexResponse {

    @Serializable
    data class GetList(
        @SerialName(KeyItems) val items: List<Item>,
        @SerialName(KeyNbPages) val nbPages: Int
    ) {

        @Serializable
        data class Item(
            @SerialName(KeyName) val indexName: IndexName,
            @SerialName(KeyCreatedAt) val createdAt: String,
            @SerialName(KeyUpdatedAt) val updatedAt: String,
            @SerialName(KeyEntries) val entries: Int,
            @SerialName(KeyDataSize) val dataSize: Int,
            @SerialName(KeyFileSize) val fileSize: Int,
            @SerialName(KeyLastBuildTimeS) val lastBuildTimeS: Int,
            @SerialName(KeyNumberOfPendingTasks) val numberOfPendingTasks: Int,
            @SerialName(KeyPendingTask) val pendingTask: Boolean,
            @SerialName(KeyReplicas) @Optional val replicas: List<String>? = null
        )
    }

    @Serializable
    data class Search(
        @SerialName(KeyResults) val responses: List<SearchResponse>
    )

    @Serializable(Batch.Companion::class)
    data class Batch(
        @SerialName(KeyTaskID) val taskIDs: List<TaskIndex>,
        @Optional @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID?>? = null
    ) {

        @Serializer(Batch::class)
        companion object : KSerializer<Batch> {

            override fun serialize(encoder: Encoder, obj: Batch) {
                val json = json {
                    KeyTaskID to json { obj.taskIDs.forEach { it.indexName.raw to it.taskID.raw } }
                    KeyObjectIDs to obj.objectIDs?.let { jsonArray { it.forEach { +it?.raw } } }
                }

                encoder.asJsonOutput().encodeJson(json)
            }

            override fun deserialize(decoder: Decoder): Batch {
                val element = decoder.asJsonInput().jsonObject
                val taskIDs = element.getObject(KeyTaskID).map { (key, entry) ->
                    TaskIndex(key.toIndexName(), entry.long.toTaskID())
                }
                val objectIDs = element.getArrayOrNull(KeyObjectIDs)?.map { it.contentOrNull?.toObjectID() }

                return Batch(taskIDs, objectIDs)
            }
        }
    }
}