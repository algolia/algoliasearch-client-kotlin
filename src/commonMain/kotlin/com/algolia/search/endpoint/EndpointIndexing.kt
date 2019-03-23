package com.algolia.search.endpoint

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.DeleteByQuery
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.Partial
import com.algolia.search.model.response.ResponseBatch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.creation.CreationObject
import com.algolia.search.model.response.deletion.DeletionObject
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionObject
import com.algolia.search.model.search.Query
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.transport.RequestOptions
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject


public interface EndpointIndexing {

    val indexName: IndexName

    suspend fun <T> saveObject(
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions? = null
    ): CreationObject

    suspend fun <T> saveObjects(
        serializer: KSerializer<T>,
        data: List<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun saveObject(
        data: JsonObject,
        requestOptions: RequestOptions? = null
    ): CreationObject

    suspend fun saveObjects(
        data: List<JsonObject>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun <T : Indexable> replaceObject(
        serializer: KSerializer<T>,
        data: T,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun <T : Indexable> replaceObjects(
        serializer: KSerializer<T>,
        data: List<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun replaceObject(
        objectID: ObjectID,
        data: JsonObject,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun replaceObjects(
        data: List<Pair<ObjectID, JsonObject>>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun replaceAllObjects(
        data: List<JsonObject>
    ): List<TaskIndex>

    suspend fun <T> replaceAllObjects(
        serializer: KSerializer<T>,
        data: List<T>
    ): List<TaskIndex>

    suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): DeletionObject

    suspend fun deleteObjects(
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun deleteObjectBy(
        query: DeleteByQuery,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): T

    suspend fun getObject(
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun getObjects(
        objectIDs: List<ObjectID>,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): ResponseObjects

    suspend fun partialUpdateObject(
        objectID: ObjectID,
        partial: Partial,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun partialUpdateObjects(
        data: List<Pair<ObjectID, Partial>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun clearObjects(
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}