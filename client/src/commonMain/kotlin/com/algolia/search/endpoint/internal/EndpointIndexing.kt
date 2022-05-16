@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.client.internal.Index
import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.internal.requestOptionsBuilder
import com.algolia.search.endpoint.EndpointIndex
import com.algolia.search.endpoint.EndpointIndexing
import com.algolia.search.exception.EmptyListException
import com.algolia.search.helper.toIndexName
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.index.Scope
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.internal.request.EmptyBody
import com.algolia.search.model.internal.request.RequestParams
import com.algolia.search.model.internal.request.RequestRequestObjects
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.response.ResponseBatch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.creation.CreationObject
import com.algolia.search.model.response.deletion.DeletionObject
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionObject
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.JsonNonStrict
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.Route
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlin.random.Random

internal class EndpointIndexingImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointIndexing {

    private suspend fun saveObject(body: String, requestOptions: RequestOptions?): CreationObject {
        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath(), requestOptions, body)
    }

    override suspend fun <T> saveObject(
        serializer: KSerializer<T>,
        record: T,
        requestOptions: RequestOptions?,
    ): CreationObject {
        return saveObject(Json.encodeToString(serializer, record), requestOptions)
    }

    override suspend fun <T> saveObjects(
        serializer: KSerializer<T>,
        records: List<T>,
        requestOptions: RequestOptions?,
    ): ResponseBatch {
        val operations = records.map { BatchOperation.AddObject.from(serializer, it) }

        return batch(operations, requestOptions)
    }

    override suspend fun saveObject(record: JsonObject, requestOptions: RequestOptions?): CreationObject {
        return saveObject(record.toString(), requestOptions)
    }

    override suspend fun saveObjects(records: List<JsonObject>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = records.map { BatchOperation.AddObject(it) }

        return batch(operations, requestOptions)
    }

    private suspend fun replaceObject(
        body: String,
        objectID: ObjectID,
        requestOptions: RequestOptions?,
    ): RevisionObject {
        return transport.request(HttpMethod.Put, CallType.Write, indexName.toPath("/$objectID"), requestOptions, body)
    }

    override suspend fun <T : Indexable> replaceObject(
        serializer: KSerializer<T>,
        record: T,
        requestOptions: RequestOptions?,
    ): RevisionObject {
        return replaceObject(Json.encodeToString(serializer, record), record.objectID, requestOptions)
    }

    override suspend fun <T : Indexable> replaceObjects(
        serializer: KSerializer<T>,
        records: List<T>,
        requestOptions: RequestOptions?,
    ): ResponseBatch {
        val operations = records.map { BatchOperation.ReplaceObject.from(serializer, it) }

        return batch(operations, requestOptions)
    }

    override suspend fun replaceObject(
        objectID: ObjectID,
        record: JsonObject,
        requestOptions: RequestOptions?,
    ): RevisionObject {
        return replaceObject(record.toString(), objectID, requestOptions)
    }

    override suspend fun replaceObjects(
        records: List<Pair<ObjectID, JsonObject>>,
        requestOptions: RequestOptions?,
    ): ResponseBatch {
        val operations = records.map { BatchOperation.ReplaceObject(it.first, it.second) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObject(objectID: ObjectID, requestOptions: RequestOptions?): DeletionObject {
        return transport.request(HttpMethod.Delete, CallType.Write, indexName.toPath("/$objectID"), requestOptions)
    }

    override suspend fun deleteObjects(objectIDs: List<ObjectID>, requestOptions: RequestOptions?): ResponseBatch {
        val operations = objectIDs.map { BatchOperation.DeleteObject(it) }

        return batch(operations, requestOptions)
    }

    override suspend fun deleteObjectsBy(query: DeleteByQuery, requestOptions: RequestOptions?): RevisionIndex {
        val path = indexName.toPath("/deleteByQuery")
        val params = RequestParams(query.toJsonNoDefaults().urlEncode())
        val body = JsonNoDefaults.encodeToString(RequestParams.serializer(), params)

        return transport.request(HttpMethod.Post, CallType.Write, path, requestOptions, body)
    }

    private suspend fun getObjectInternal(
        objectID: ObjectID,
        attributes: List<Attribute>?,
        requestOptions: RequestOptions?,
    ): JsonObject {
        val attributesToRetrieve = attributes?.let { Json.encodeToString(ListSerializer(Attribute), it.toList()) }
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.AttributesToRetrieve, attributesToRetrieve)
        }

        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/$objectID"), options)
    }

    override suspend fun getObject(
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?,
    ): JsonObject {
        return getObjectInternal(objectID, attributesToRetrieve, requestOptions = requestOptions)
    }

    override suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?,
    ): T {
        return getObjectInternal(objectID, attributesToRetrieve, requestOptions = requestOptions).let {
            JsonNonStrict.decodeFromJsonElement(serializer, it)
        }
    }

    override suspend fun getObjects(
        objectIDs: List<ObjectID>,
        attributesToRetrieve: List<Attribute>?,
        requestOptions: RequestOptions?,
    ): ResponseObjects {
        val requests = objectIDs.map { RequestObjects(indexName, it, attributesToRetrieve) }
        val body = JsonNoDefaults.encodeToString(RequestRequestObjects.serializer(), RequestRequestObjects(requests))

        return transport.request(HttpMethod.Post, CallType.Read, "${Route.IndexesV1}/*/objects", requestOptions, body)
    }

    override suspend fun partialUpdateObject(
        objectID: ObjectID,
        partial: Partial,
        createIfNotExists: Boolean?,
        requestOptions: RequestOptions?,
    ): RevisionObject {
        val path = indexName.toPath("/$objectID/partial")
        val options = requestOptionsBuilder(requestOptions) {
            parameter(Key.CreateIfNotExists, createIfNotExists)
        }
        val body = Json.encodeToJsonElement(Partial, partial).toString()

        return transport.request(HttpMethod.Post, CallType.Write, path, options, body)
    }

    override suspend fun partialUpdateObjects(
        partials: List<Pair<ObjectID, Partial>>,
        createIfNotExists: Boolean,
        requestOptions: RequestOptions?,
    ): ResponseBatch {
        val operations =
            partials.map { BatchOperation.PartialUpdateObject.from(it.first, it.second, createIfNotExists) }

        return batch(operations, requestOptions)
    }

    override suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions?,
    ): ResponseBatch {
        if (batchOperations.isEmpty()) throw EmptyListException("batchOperations")
        val requests = Json.encodeToJsonElement(ListSerializer(BatchOperation), batchOperations)
        val body = buildJsonObject { put(Key.Requests, requests) }.toString()

        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/batch"), requestOptions, body)
    }

    override suspend fun clearObjects(requestOptions: RequestOptions?): RevisionIndex {
        return transport.request(HttpMethod.Post, CallType.Write, indexName.toPath("/clear"), requestOptions, EmptyBody)
    }

    override suspend fun replaceAllObjects(
        records: List<JsonObject>,
    ): List<TaskIndex> {
        val operations = records.map { BatchOperation.AddObject(it) }

        return replaceAllObjectsInternal(operations)
    }

    override suspend fun <T> replaceAllObjects(
        serializer: KSerializer<T>,
        records: List<T>,
    ): List<TaskIndex> {
        val operations = records.map { BatchOperation.AddObject.from(serializer, it) }

        return replaceAllObjectsInternal(operations)
    }

    private suspend fun replaceAllObjectsInternal(batchOperations: List<BatchOperation>): List<TaskIndex> {
        if (batchOperations.isEmpty()) throw EmptyListException("batchOperations")
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

/**
 * Create an [EndpointIndex] instance.
 */
internal fun EndpointIndexing(
    transport: Transport,
    indexName: IndexName,
): EndpointIndexing = EndpointIndexingImpl(transport, indexName)
