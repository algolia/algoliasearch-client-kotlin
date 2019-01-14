package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointIndexing
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

    override suspend fun addObject(json: JsonObject, requestOptions: RequestOptions?): TaskCreateObject {
        return addObject(json.toString(), requestOptions)
    }

    private suspend fun updateObject(
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
        return updateObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun replaceObject(
        json: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(json.toString(), objectID, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteObjectBy(query: Query, requestOptions: RequestOptions?): TaskUpdateIndex {
        val copy = query.clone()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/deleteByQuery")) { path ->
            httpClient.post<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                body = json { KeyParams to copy.encodeNoNulls().urlEncode() }.toString()
            }
        }
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        vararg attributes: Attribute,
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
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, *attributes, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        vararg attributes: Attribute,
        requestOptions: RequestOptions?
    ): T? {
        return getObjectInternal(objectID, *attributes, requestOptions = requestOptions).let {
            Json.nonstrict.fromJson(it, serializer)
        }
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): TaskUpdateIndex {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/clear")) { path ->
            httpClient.post<TaskUpdateIndex>(path) {
                setRequestOptions(requestOptions)
                body = ""
            }
        }
    }

    private suspend fun updateObject(
        payload: String,
        objectID: ObjectID,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID/partial")) { path ->
            httpClient.post<TaskUpdateObject>(path) {
                setRequestOptions(requestOptions)
                parameter(KeyCreateIfNotExists, createIfNotExists)
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(Json.stringify(serializer, data), data.objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObject(
        json: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        return updateObject(json.toString(), objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObject(
        objectID: ObjectID,
        partialUpdate: PartialUpdate,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): TaskUpdateObject {
        val payload = Json.plain.toJson(partialUpdate, PartialUpdate).toString()

        return updateObject(payload, objectID, createIfNotExists, requestOptions)
    }

    override suspend fun batch(
        batchOperation: BatchOperation,
        vararg additionalBatchOperations: BatchOperation,
        requestOptions: RequestOptions?
    ): TaskBatchOperation {
        val requests = Json.plain.toJson((listOf(batchOperation) + additionalBatchOperations), BatchOperation.list)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/batch")) { path ->
            httpClient.post<TaskBatchOperation>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }
}