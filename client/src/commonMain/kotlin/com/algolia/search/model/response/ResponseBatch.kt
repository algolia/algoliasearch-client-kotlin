package com.algolia.search.model.response

import com.algolia.search.endpoint.EndpointAdvanced
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseBatch(
    /**
     * The [TaskID] which can be used with the [EndpointAdvanced.waitTask] method.
     */
    @SerialName(Key.TaskID) override val taskID: TaskID,
    /**
     * The [ObjectID] records targeted by a batch operation. If an [ObjectID] was not found, for example after a
     * [EndpointIndexing.replaceObjects] call, it will be `null` at the same index it was passed in the "records"
     * request parameter.
     *
     * Example where ObjectID("B") doesn't exist in the index:
     *
     * ```
     * val records = listOf(
     *    ObjectID("A") to json { "keyA" to "valueA" },
     *    ObjectID("B") to json { "keyB" to "valueB" },
     *    ObjectID("C") to json { "keyC" to "valueC" }
     * )
     *
     * val response = index.replaceObjects(records)
     *
     * response[0] == ObjectID("A")
     * response[1] == null
     * response[2] == ObjectID("C")
     * ```
     */
    @SerialName(Key.ObjectIDs) val objectIDs: List<ObjectID?>
) : Task
