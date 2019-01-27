package com.algolia.search.endpoint

import com.algolia.search.client.RequestOptions
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.BatchOperation
import com.algolia.search.model.indexing.Indexable
import com.algolia.search.model.indexing.PartialUpdate
import com.algolia.search.model.search.Query
import com.algolia.search.response.ResponseBatch
import com.algolia.search.response.creation.CreationObject
import com.algolia.search.response.deletion.DeletionObject
import com.algolia.search.response.revision.RevisionIndex
import com.algolia.search.response.revision.RevisionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject


interface EndpointIndexing {

    val indexName: IndexName

    suspend fun <T> addObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): CreationObject

    suspend fun <T> addObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun addObject(
        data: JsonObject,
        requestOptions: RequestOptions? = null
    ): CreationObject

    suspend fun addObjects(
        data: List<JsonObject>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun <T : Indexable> replaceObject(
        data: T,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun <T : Indexable> replaceObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun replaceObject(
        data: JsonObject,
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun replaceObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): DeletionObject

    suspend fun deleteObjects(
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun deleteObjectBy(
        query: Query,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    suspend fun <T : Indexable> getObject(
        objectID: ObjectID,
        serializer: KSerializer<T>,
        attributes: List<Attribute> = listOf(),
        requestOptions: RequestOptions? = null
    ): T?

    suspend fun getObject(
        objectID: ObjectID,
        attributes: List<Attribute> = listOf(),
        requestOptions: RequestOptions? = null
    ): JsonObject

    suspend fun <T : Indexable> updateObject(
        data: T,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun <T : Indexable> updateObjects(
        data: List<T>,
        serializer: KSerializer<T>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun updateObject(
        data: JsonObject,
        objectID: ObjectID,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun updateObjects(
        data: List<Pair<JsonObject, ObjectID>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun partialUpdateObject(
        partialUpdate: PartialUpdate,
        objectID: ObjectID,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    suspend fun partialUpdateObjects(
        data: List<Pair<PartialUpdate, ObjectID>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch
}