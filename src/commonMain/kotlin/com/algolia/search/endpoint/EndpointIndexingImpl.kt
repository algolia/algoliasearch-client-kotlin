package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.request.RequestRequestObjects
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


internal class EndpointIndexingImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointIndexing,
    APIWrapper by api {

    private suspend fun saveObject(payload: String, requestOptions: RequestOptions?): CreationObject {
        return retryWrite(requestOptions, indexName.toPath()) { url ->
            httpClient.post<CreationObject>(url) {
                body = payload
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun <T> saveObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): CreationObject {
        return saveObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun <T> saveObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.AddObject.from(it, serializer) }

        return batch(operations, requestOptions)
    }

    override suspend fun saveObject(data: JsonObject, requestOptions: RequestOptions?): CreationObject {
        return saveObject(data.toString(), requestOptions)
    }

    override suspend fun saveObjects(data: List<JsonObject>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = data.map { BatchOperation.AddObject(it) }

        return batch(operations, requestOptions)
    }

    private suspend fun replaceObject(
        payload: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return retryWrite(requestOptions, indexName.toPath("/$objectID")) { url ->
            httpClient.put<RevisionObject>(url) {
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
        return retryWrite(requestOptions, indexName.toPath("/$objectID")) { url ->
            httpClient.delete<DeletionObject>(url) {
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

        return retryWrite(requestOptions, indexName.toPath("/deleteByQuery")) { url ->
            httpClient.post<RevisionIndex>(url) {
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

        return retryRead(requestOptions, indexName.toPath("/$objectID")) { url ->
            httpClient.get<JsonObject>(url) {
                parameter(KeyAttributesToRetrieve, attributesToRetrieve)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun getObject(
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, attributesToRetrieve, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?
    ): T {
        return getObjectInternal(objectID, attributesToRetrieve, requestOptions = requestOptions).let {
            Json.nonstrict.fromJson(serializer, it)
        }
    }

    override suspend fun getObjects(
        objectIDs: List<ObjectID>,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?
    ): ResponseObjects {
        val requests = objectIDs.map { RequestObjects(indexName, it, attributesToRetrieve) }
        val bodyString = JsonNoNulls.stringify(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return retryRead(requestOptions, "/1/indexes/*/objects") { url ->
            httpClient.post<ResponseObjects>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        val payload = Json.plain.toJson(PartialUpdate, partialUpdate).toString()

        return retryWrite(requestOptions, indexName.toPath("/$objectID/partial")) { url ->
            httpClient.post<RevisionObject>(url) {
                body = payload
                parameter(KeyCreateIfNotExists, createIfNotExists)
                setRequestOptions(requestOptions)
            }
        }
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

        return retryWrite(requestOptions, indexName.toPath("/batch")) { url ->
            httpClient.post<ResponseBatch>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): RevisionIndex {
        return retryWrite(requestOptions, indexName.toPath("/clear")) { url ->
            httpClient.post<RevisionIndex>(url) {
                body = ""
                setRequestOptions(requestOptions)
            }
        }
    }
}