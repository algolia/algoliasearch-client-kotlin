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
import com.algolia.search.model.task.TaskIndex
import com.algolia.search.transport.RequestOptions
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonObject

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/indexing/?language=kotlin]
 */
public interface EndpointIndexing {

    public val indexName: IndexName

    /**
     * Add a new record to an index.
     * This method allows you to create records on your index by sending one or more objects.
     * Each object contains a set of attributes and values, which represents a full record on an index.
     * There is no limit to the number of objects that can be passed, but a size limit of 1 GB on the total request.
     * For performance reasons, it is recommended to push batches of ~10 MB of payload.
     * Batching records allows you to reduce the number of network calls required for multiple operations.
     * But note that each indexed object counts as a single indexing operation.
     * When adding large numbers of objects, or large sizes, be aware of our rate limit.
     * You’ll know you’ve reached the rate limit when you start receiving errors on your indexing operations.
     * This can only be resolved if you wait before sending any further indexing operations.
     *
     * @param serializer [KSerializer] of type [T] for serialization.
     * @param record The record [T] to save.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T> saveObject(
        serializer: KSerializer<T>,
        record: T,
        requestOptions: RequestOptions? = null
    ): CreationObject

    /**
     * Add multiple objects to an index.
     *
     * @see saveObject
     *
     * @param serializer [KSerializer] of type [T] for serialization.
     * @param records The records [T] to save.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T> saveObjects(
        serializer: KSerializer<T>,
        records: List<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Add a schemaless object to an index.
     *
     * @see saveObject
     *
     * @param record The record [JsonObject] to save.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveObject(
        record: JsonObject,
        requestOptions: RequestOptions? = null
    ): CreationObject

    /**
     * Add multiple schemaless objects to an index.
     *
     * @see saveObject
     *
     * @param records The list of records [JsonObject] to save.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveObjects(
        records: List<JsonObject>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Replace an existing object with an updated set of attributes.
     * The save method is used to redefine the entire set of an object’s attributes (except of course its [ObjectID]).
     * In other words, it fully replaces an existing object.
     * Saving objects has the same effect as the add objects method if you specify objectIDs for every record.
     * This method differs from partial update objects in a significant way:
     * With save objects you define an object’s full set of attributes. Attributes not specified will no longer exist.
     * For example, if an existing object contains attribute X, but X is not defined in a later update call, attribute X
     * will no longer exist for that object.
     * In contrast, with partial update objects you can single out one or more attributes, and either remove them,
     * add them, or update their content. Additionally, attributes that already exist but are not specified in a partial update are not impacted.
     * When updating large numbers of objects, or large sizes, be aware of our rate limit.
     * You’ll know you’ve reached the rate limit when you start receiving errors on your indexing operations.
     * This can only be resolved if you wait before sending any further indexing operations.
     *
     * @param serializer [KSerializer] of type [T] for serialization.
     * @param record The record [T] to replace.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun <T : Indexable> replaceObject(
        serializer: KSerializer<T>,
        record: T,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    /**
     * Replace multiple existing objects with an updated set of attributes.
     *
     * @see replaceObject
     *
     * @param serializer [KSerializer] of type [T] for serialization.
     * @param records The records [T] to replace.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : Indexable> replaceObjects(
        serializer: KSerializer<T>,
        records: List<T>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Replace a schemaless object with an updated set of attributes.
     *
     * @see replaceObject
     *
     * @param objectID The [ObjectID] to identify the object.
     * @param record The record [JsonObject] to replace.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun replaceObject(
        objectID: ObjectID,
        record: JsonObject,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    /**
     * Replace multiple schemaless objects with an updated set of attributes.
     *
     * @see replaceObject
     *
     * @param records The list of records to replace.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun replaceObjects(
        records: List<Pair<ObjectID, JsonObject>>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Push a new set of objects and remove all previous ones. Settings, synonyms and query rules are untouched.
     * Replace all objects in an index without any downtime.
     * Internally, this method copies the existing index settings, synonyms and query rules and indexes all
     * passed objects. Finally, the existing index is replaced by the temporary one.
     *
     * @param serializer [KSerializer] of type [T] for serialization.
     * @param records The list of records to replace.
     */
    public suspend fun <T> replaceAllObjects(
        serializer: KSerializer<T>,
        records: List<T>
    ): List<TaskIndex>

    /**
     * Push a new set of schemaless objects and remove all previous ones.
     *
     * @see replaceAllObjects
     */
    public suspend fun replaceAllObjects(
        records: List<JsonObject>
    ): List<TaskIndex>

    /**
     * Remove an object from an index using its [ObjectID].
     *
     * @param objectID The [ObjectID] to identify the record.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun deleteObject(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): DeletionObject

    /**
     * Remove multiple objects from an index using their [ObjectID].
     *
     * @param objectIDs The [ObjectID] to identify the record.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun deleteObjects(
        objectIDs: List<ObjectID>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Remove all objects matching a [DeleteByQuery].
     * This method enables you to delete one or more objects based on filters (numeric, facet, tag or geo queries).
     * It does not accept empty filters or a query.
     * If you have a way to fetch the list of [ObjectID] you want to delete, use the [deleteObjects] method instead as
     * it is more performant.
     * The delete by method only counts as 1 operation - even if it deletes more than one object. This is exceptional;
     * most indexing options that affect more than one object normally count each object as a separate operation.
     * When deleting large numbers of objects, or large sizes, be aware of our rate limit. You’ll know you’ve reached
     * the rate limit when you start receiving errors on your indexing operations. This can only be resolved if you wait
     * before sending any further indexing operations.
     *
     * @param query [DeleteByQuery] to match records for deletion.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun deleteObjectsBy(
        query: DeleteByQuery,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Get one record using its [ObjectID].
     *
     * @param serializer [KSerializer] of type [T] for deserialization.
     * @param objectID The [ObjectID] to identify the record.
     * @param attributesToRetrieve Specify a list of [Attribute] to retrieve. This list will apply to all records.
     * If you don’t specify any attributes, every attribute will be returned.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun <T : Indexable> getObject(
        serializer: KSerializer<T>,
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): T

    /**
     * Schemaless version of [getObject]. Use this method if you don't know the expected schema of the record you want
     * to retrieve.
     *
     * @param objectID The [ObjectID] to identify the record.
     * @param attributesToRetrieve Specify a list of [Attribute] to retrieve. This list will apply to all records.
     * If you don’t specify any attributes, every attribute will be returned.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getObject(
        objectID: ObjectID,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): JsonObject

    /**
     * Get multiple records using their [ObjectID].
     *
     * @see getObject
     */
    public suspend fun getObjects(
        objectIDs: List<ObjectID>,
        attributesToRetrieve: List<Attribute>? = null,
        requestOptions: RequestOptions? = null
    ): ResponseObjects

    /**
     * Update one or more attributes of an existing record.
     * This method enables you to update only a part of an object by singling out one or more attributes of an existing
     * object and performing the following actions:
     * - add new attributes
     * - update the content of existing attributes
     *
     * Specifying existing attributes will update them in the object, while specifying new attributes will add them.
     * You need to use the save objects method if you want to completely redefine an existing object, replace an object
     * with a different one, or remove attributes. You cannot individually remove attributes.
     * Nested attributes cannot be individually updated. If you specify a nested attribute, it will be treated as a
     * replacement of its first-level ancestor.
     * To change nested attributes, you will need to use the save object method. You can initially get the object’s data
     * either from your own data or by using the get object method.
     * The same can be said about array attributes: you cannot update individual elements of an array.
     * If you have a record in which one attribute is an array, you will need to retrieve the record’s array,
     * change the element(s) of the array, and then resend the full array using this method.
     * When updating large numbers of objects, or large sizes, be aware of our rate limit.
     * You’ll know you’ve reached the rate limit when you start receiving errors on your indexing operations.
     * This can only be resolved if you wait before sending any further indexing operations.
     *
     * @param objectID The [ObjectID] identifying the record to partially update.
     * @param partial [Partial]
     * @param createIfNotExists When true, a partial update on a nonexistent record will create the record
     * (generating the objectID and using the attributes as defined in the record). When false, a partial
     * update on a nonexistent record will be ignored (but no error will be sent back).
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun partialUpdateObject(
        objectID: ObjectID,
        partial: Partial,
        createIfNotExists: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionObject

    /**
     * Update one or more attributes of existing records.
     *
     * @param partials A list of records to partially update each record.
     * @param createIfNotExists
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun partialUpdateObjects(
        partials: List<Pair<ObjectID, Partial>>,
        createIfNotExists: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Perform several indexing operations in one API call.
     * This method enables you to batch multiple different indexing operations in one API, like add or delete records
     * potentially targeting multiple indices.
     *
     * You would use this method to:
     * - reduce latency - only one network trip is required for multiple operation
     * - ensure data integrity - all operations inside the batch will be executed atomically.
     *   Meaning that instead of deleting 30 objects then adding 20 new objects in two operations,
     *   we do both operations in one go. This will remove the time during which an index is in an inconsistent
     *   state and could be a great alternative to doing an atomic reindexing using a temporary index
     * - When batching of a large numbers of objects, or large sizes, be aware of our rate limit.
     *   You’ll know you’ve reached the rate limit when you start receiving errors on your indexing operations.
     *   This can only be resolved if you wait before sending any further indexing operations.
     *
     * @param batchOperations List of [BatchOperation]
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun batch(
        batchOperations: List<BatchOperation>,
        requestOptions: RequestOptions? = null
    ): ResponseBatch

    /**
     * Clear the records of an index without affecting its settings.
     * This method enables you to delete an index’s contents (records) without removing any settings, rules and synonyms.
     * If you want to remove the entire index and not just its records, use the delete method instead.
     * Clearing an index will have no impact on its Analytics data because you cannot clear an index’s analytics data.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun clearObjects(
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}
