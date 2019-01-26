package com.algolia.search.saas.client

import com.algolia.search.saas.model.*
import com.algolia.search.saas.model.search.Query
import com.algolia.search.saas.endpoint.EndpointIndexing
import com.algolia.search.saas.model.common.TaskDelete
import com.algolia.search.saas.model.common.TaskUpdate
import com.algolia.search.saas.model.indexing.*
import com.algolia.search.saas.query.clone
import com.algolia.search.saas.serialize.*
import io.ktor.client.request.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list


internal class ClientIndexing(
    val client: Client,
    override val indexName: IndexName
) : EndpointIndexing,
    Client by client {

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): TaskCreateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.post<TaskCreateObject>(path) {
                setRequestOptions(requestOptions)
                body = payload
            }
        }
    }

    override suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskCreateObject {
        return addObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun <T> addObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.AddObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun addObject(data: JsonObject, requestOptions: RequestOptions?): TaskCreateObject {
        return addObject(data.toString(), requestOptions)
    }

    override suspend fun addObjects(data: List<JsonObject>, requestOptions: RequestOptions?): TaskBatchOperation {
        val operations = data.map { BatchOperation.AddObject(it) }

        return batch(operations, requestOptions)
    }

    private suspend fun replaceObject(
        payload: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.put<TaskUpdateObject>(path) {
                setRequestOptions(requestOptions)
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return replaceObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun <T : Indexable> replaceObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.ReplaceObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun replaceObject(
        data: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return replaceObject(data.toString(), objectID, requestOptions)
    }

    override suspend fun replaceObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.ReplaceObject(it.first, it.second) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteObjects(objectIDs: List<ObjectID>, requestOptions: RequestOptions?): TaskBatchOperation {
        val operations = objectIDs.map { BatchOperation.DeleteObject(it) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObjectBy(query: Query, requestOptions: RequestOptions?): TaskUpdate {
        val copy = query.clone()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/deleteByQuery")) { path ->
            httpClient.post<TaskUpdate>(path) {
                setRequestOptions(requestOptions)
                body = json { KeyParams to copy.encodeNoNulls().urlEncode() }.toString()
            }
        }
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        attributes: List<Attribute>,
        requestOptions: RequestOptions?
    ): JsonObject {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.get<JsonObject>(path) {
                setRequestOptions(requestOptions)
                if (attributes.isNotEmpty()) {
                    parameter(KeyAttributesToRetrieve, Json.stringify(Attribute.list, attributes.toList()))
                }
            }
        }
    }

    override suspend fun getObject(
        objectID: ObjectID,
        attributes: List<Attribute>,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, attributes, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        attributes: List<Attribute>,
        requestOptions: RequestOptions?
    ): T? {
        return getObjectInternal(objectID, attributes, requestOptions = requestOptions).let {
            Json.nonstrict.fromJson(serializer, it)
        }
    }

    private suspend fun updateObject(
        payload: String,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID/partial")) { path ->
            httpClient.post<TaskUpdateObject>(path) {
                setRequestOptions(requestOptions)
                createIfNotExists?.let { parameter(KeyCreateIfNotExists, createIfNotExists) }
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(Json.stringify(serializer, data), data.objectID, createIfNotExists, requestOptions)
    }

    override suspend fun <T : Indexable> updateObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.UpdateObject.from(it, serializer, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun updateObject(
        data: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(data.toString(), objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.UpdateObject(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        val payload = Json.plain.toJson(PartialUpdate, partialUpdate).toString()

        return updateObject(payload, objectID, createIfNotExists, requestOptions)
    }

    override suspend fun partialUpdateObjects(
        data: List<Pair<PartialUpdate, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val operations = data.map { BatchOperation.UpdateObject.from(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val requests = Json.plain.toJson(BatchOperation.list, batchOperations)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/batch")) { path ->
            httpClient.post<TaskBatchOperation>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }
}