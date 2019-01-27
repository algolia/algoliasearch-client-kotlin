package com.algolia.search.client

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.IndexingResponse
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.model.search.Query
import com.algolia.search.query.clone
import com.algolia.search.serialize.*
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

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): IndexingResponse.CreateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.post<IndexingResponse.CreateObject>(path) {
                setRequestOptions(requestOptions)
                body = payload
            }
        }
    }

    override suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): IndexingResponse.CreateObject {
        return addObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun <T> addObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.AddObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun addObject(data: JsonObject, requestOptions: RequestOptions?): IndexingResponse.CreateObject {
        return addObject(data.toString(), requestOptions)
    }

    override suspend fun addObjects(data: List<JsonObject>, requestOptions: RequestOptions?): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.AddObject(it) }

        return batch(operations, requestOptions)
    }

    private suspend fun replaceObject(
        payload: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): IndexingResponse.UpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.put<IndexingResponse.UpdateObject>(path) {
                setRequestOptions(requestOptions)
                body = payload
            }
        }
    }

    override suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): IndexingResponse.UpdateObject {
        return replaceObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun <T : Indexable> replaceObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.ReplaceObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun replaceObject(
        data: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): IndexingResponse.UpdateObject {
        return replaceObject(data.toString(), objectID, requestOptions)
    }

    override suspend fun replaceObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.ReplaceObject(it.first, it.second) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): IndexingResponse.Delete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.delete<IndexingResponse.Delete>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteObjects(objectIDs: List<ObjectID>, requestOptions: RequestOptions?): IndexingResponse.Batch {
        val operations = objectIDs.map { BatchOperation.DeleteObject(it) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObjectBy(query: Query, requestOptions: RequestOptions?): IndexingResponse.Update {
        val copy = query.clone()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/deleteByQuery")) { path ->
            httpClient.post<IndexingResponse.Update>(path) {
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
    ): IndexingResponse.UpdateObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID/partial")) { path ->
            httpClient.post<IndexingResponse.UpdateObject>(path) {
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
    ): IndexingResponse.UpdateObject {
        return updateObject(Json.stringify(serializer, data), data.objectID, createIfNotExists, requestOptions)
    }

    override suspend fun <T : Indexable> updateObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.UpdateObject.from(it, serializer, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun updateObject(
        data: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): IndexingResponse.UpdateObject {
        return updateObject(data.toString(), objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.UpdateObject(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): IndexingResponse.UpdateObject {
        val payload = Json.plain.toJson(PartialUpdate, partialUpdate).toString()

        return updateObject(payload, objectID, createIfNotExists, requestOptions)
    }

    override suspend fun partialUpdateObjects(
        data: List<Pair<PartialUpdate, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val operations = data.map { BatchOperation.UpdateObject.from(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions?
    ): IndexingResponse.Batch {
        val requests = Json.plain.toJson(BatchOperation.list, batchOperations)
        val json = json { KeyRequests to requests }

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/batch")) { path ->
            httpClient.post<IndexingResponse.Batch>(path) {
                setRequestOptions(requestOptions)
                body = json.toString()
            }
        }
    }
}