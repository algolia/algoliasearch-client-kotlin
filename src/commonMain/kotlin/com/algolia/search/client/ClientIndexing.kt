package com.algolia.search.client

import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.response.ResponseBatch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.creation.CreationObject
import com.algolia.search.model.response.deletion.DeletionObject
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionObject
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

    private suspend fun addObject(payload: String, requestOptions: RequestOptions?): CreationObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes()) { path ->
            httpClient.post<CreationObject>(path) {
                body = payload
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): CreationObject {
        return addObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun <T> addObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.AddObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun addObject(data: JsonObject, requestOptions: RequestOptions?): CreationObject {
        return addObject(data.toString(), requestOptions)
    }

    override suspend fun addObjects(data: List<JsonObject>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = data.map { BatchOperation.AddObject(it) }

        return batch(operations, requestOptions)
    }

    private suspend fun replaceObject(
        payload: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.put<RevisionObject>(path) {
                body = payload
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return replaceObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun <T : Indexable> replaceObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.ReplaceObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun replaceObject(
        data: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return replaceObject(data.toString(), objectID, requestOptions)
    }

    override suspend fun replaceObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.ReplaceObject(it.first, it.second) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): DeletionObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.delete<DeletionObject>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteObjects(objectIDs: List<ObjectID>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = objectIDs.map { BatchOperation.DeleteObject(it) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObjectBy(query: Query, requestOptions: RequestOptions?): RevisionIndex {
        val copy = query.clone()
        val bodyString = json { KeyParams to copy.toJsonNoDefaults().urlEncode() }.toString()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/deleteByQuery")) { path ->
            httpClient.post<RevisionIndex>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        val attributesToRetrieve = attributes?.let { Json.stringify(Attribute.list, it.toList()) }

        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/$objectID")) { path ->
            httpClient.get<JsonObject>(path) {
                parameter(KeyAttributesToRetrieve, attributesToRetrieve)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getObject(
        objectID: ObjectID,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, attributes, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): T? {
        return getObjectInternal(objectID, attributes, requestOptions = requestOptions).let {
            Json.nonstrict.fromJson(serializer, it)
        }
    }

    override suspend fun getObjects(
        objectIDs: List<ObjectID>,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): ResponseObjects {
        val requests = objectIDs.map { RequestObjects(indexName, it, attributes) }
        val bodyString = json { KeyRequests to Json.plain.toJson(RequestObjects.list, requests) }.toString()

        return read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/objects") { path ->
            httpClient.post<ResponseObjects>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    private suspend fun updateObject(
        payload: String,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/$objectID/partial")) { path ->
            httpClient.post<RevisionObject>(path) {
                body = payload
                parameter(KeyCreateIfNotExists, createIfNotExists)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return updateObject(Json.stringify(serializer, data), data.objectID, createIfNotExists, requestOptions)
    }

    override suspend fun <T : Indexable> updateObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.UpdateObject.from(it, serializer, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun updateObject(
        data: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return updateObject(data.toString(), objectID, createIfNotExists, requestOptions)
    }

    override suspend fun updateObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.UpdateObject(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        val payload = Json.plain.toJson(PartialUpdate, partialUpdate).toString()

        return updateObject(payload, objectID, createIfNotExists, requestOptions)
    }

    override suspend fun partialUpdateObjects(
        data: List<Pair<PartialUpdate, ObjectID>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.UpdateObject.from(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val requests = Json.plain.toJson(BatchOperation.list, batchOperations)
        val bodyString = json { KeyRequests to requests }.toString()

        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/batch")) { path ->
            httpClient.post<ResponseBatch>(path) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }
}