package com.algolia.search.endpoint

import com.algolia.search.client.Index
import com.algolia.search.configuration.CallType
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.index.Scope
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.request.RequestParams
import com.algolia.search.model.request.RequestRequestObjects
import com.algolia.search.model.response.ResponseBatch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.creation.CreationObject
import com.algolia.search.model.response.deletion.DeletionObject
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionObject
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import kotlin.random.Random


internal class EndpointIndexingImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointIndexing {

    private suspend fun saveObject(body: String, requestOptions: RequestOptions?): CreationObject {
        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath(), requestOptions, body)
    }

    override suspend fun <T> saveObject(
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions?
    ): CreationObject {
        return saveObject(Json.stringify(serializer, data), requestOptions)
    }

    override suspend fun <T> saveObjects(
        serializer: KSerializer<T>,
        data: List<T>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.AddObject.from(serializer, it) }

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
        body: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return transport.request(HttpMethod.Put, CallType.Write, indexName.toPath("/$objectID"), requestOptions, body)
    }

    override suspend fun <T : Indexable> replaceObject(
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return replaceObject(Json.stringify(serializer, data), data.objectID, requestOptions)
    }

    override suspend fun <T : Indexable> replaceObjects(
        serializer: KSerializer<T>,
        data: List<T>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.ReplaceObject.from(serializer, it) }

        return batch(operations, requestOptions)
    }

    override suspend fun replaceObject(
        objectID: ObjectID,
        data: JsonObject,
        requestOptions: RequestOptions?
    ): RevisionObject {
        return replaceObject(data.toString(), objectID, requestOptions)
    }

    override suspend fun replaceObjects(
        data: List<Pair<ObjectID, JsonObject>>,
        requestOptions: RequestOptions?
    ): ResponseBatch {
        val operations = data.map { BatchOperation.ReplaceObject(it.first, it.second) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): DeletionObject {
        return transport.request(HttpMethod.Delete, CallType.Write, indexName.toPath("/$objectID"), requestOptions)
    }

    override suspend fun deleteObjects(objectIDs: List<ObjectID>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = objectIDs.map { BatchOperation.DeleteObject(it) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObjectBy(query: Query, requestOptions: RequestOptions?): RevisionIndex {
        val path = indexName.toPath("/deleteByQuery")
        val params = RequestParams(query.toJsonNoDefaults().urlEncode())
        val body = Json.noDefaults.stringify(RequestParams.serializer(), params)

        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        val attributesToRetrieve = attributes?.let { Json.stringify(Attribute.list, it.toList()) }
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyAttributesToRetrieve, attributesToRetrieve)
        }

        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/$objectID"), options)
    }

    override suspend fun getObject(
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?
    ): JsonObject {
        return getObjectInternal(objectID, attributesToRetrieve, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectID: ObjectID,
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
        val body = Json.noDefaults.stringify(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return transport.request(HttpMethod.Post, CallType.Read, "$RouteIndexesV1/*/objects", requestOptions, body)
    }

    override suspend fun partialUpdateObject(
        objectID: ObjectID,
        partial: Partial,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?
    ): RevisionObject {
        val path = indexName.toPath("/$objectID/partial")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyCreateIfNotExists, createIfNotExists)
        }
        val body = Json.plain.toJson(Partial, partial).toString()

        return transport.request(HttpMethod.Post, CallType.Write, path, options, body)
    }

    override suspend fun partialUpdateObjects(
        data: List<Pair<ObjectID, Partial>>,
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
        val body = json { KeyRequests to requests }.toString()

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/batch"), requestOptions, body)
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): RevisionIndex {
        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/clear"), requestOptions, "")
    }

    override suspend fun replaceAllObjects(
        data: List<JsonObject>
    ): List<TaskIndex> {
        val operations = data.map { BatchOperation.AddObject(it) }

        return replaceAllObjectsInternal(operations)
    }

    override suspend fun <T> replaceAllObjects(
        serializer: KSerializer<T>,
        data: List<T>
    ): List<TaskIndex> {
        val operations = data.map { BatchOperation.AddObject.from(serializer, it) }

        return replaceAllObjectsInternal(operations)
    }

    private suspend fun replaceAllObjectsInternal(batchOperations: List<BatchOperation>): List<TaskIndex> {
        val indexSource = Index(transport, indexName)
        val indexDestination = Index(transport, "${indexName}_tmp_${Random.nextInt()}".toIndexName())
        val scopes = listOf(Scope.Settings, Scope.Rules, Scope.Synonyms)

        return mutableListOf<TaskIndex>().also {
            it += TaskIndex(indexName, indexSource.copyIndex(indexDestination.indexName, scopes).taskID)
            it += TaskIndex(indexDestination.indexName, indexDestination.batch(batchOperations).taskID)
            it += TaskIndex(indexDestination.indexName, indexDestination.moveIndex(indexName).taskID)
        }
    }
}