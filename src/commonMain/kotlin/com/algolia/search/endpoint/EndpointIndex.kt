package com.algolia.search.endpoint

import com.algolia.search.client.Index
import com.algolia.search.model.IndexName
import com.algolia.search.model.index.Scope
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.settings.Settings
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/manage-indices/?language=kotlin]
 */
public interface EndpointIndex {

    /**
     * The [IndexName] used by [Index] to perform operations on.
     */
    public val indexName: IndexName

    /**
     * Make a copy of an index, including its objects, settings, synonyms, and query rules.
     * This method enables you to copy the entire index (objects, settings, synonyms, and rules) OR one or more of the following index elements:
     * - setting
     * - synonyms
     * - and rules (query rules)
     *
     * You can control which of these are copied by using the scope parameter.
     * The copy command will overwrite the destination index. This means everything will be lost in the destination
     * index except its API keys and Analytics data.
     * Regarding the API Keys, the source’s API Keys will be merged with the existing keys of the destination index.
     * Copying an index will have no impact on Analytics data because you cannot copy an index’s Analytics data.
     * Regarding replicas:
     * - Copying an index having replicas is possible, but the replicas will not be copied, only the data.
     *   The destination index will not have replicas.
     * - Copying to a existing index having replicas is not possible.
     *
     * @param destination [IndexName] of the destination [Index].
     * @param scopes List of [Scope]. If omitted, then all objects and all [Scope] are copied.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun copyIndex(
        destination: IndexName,
        scopes: List<Scope>? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Rename an index. Normally used to reindex your data atomically, without any down time.
     * The move index method is a safe and atomic way to rename an index.
     * By using this method, you can keep your existing service running while the data from the old index is being
     * imported into the new index.
     * Moving an index overrides the objects and settings of the destination index.
     * Regarding replicas:
     * - Moving to an index having replicas is possible, it will replace the destination index data with the source
     *   index data, and the data will be propagated to the replica
     * - Moving an index having replicas is not possible, as it would break the relationship with its replicas
     * - Moving an index will have no impact on Analytics data because you cannot move an index’s Analytics data.
     *
     * @param destination [IndexName] of the destination [Index].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun moveIndex(
        destination: IndexName,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Delete an index and all its settings, including links to its replicas.
     * This method not only removes an index from your application, it also removes its metadata and configured settings
     * (like searchable attributes or custom ranking).
     * If the index has replicas, they will be preserved but will no longer be linked to their primary index.
     * Instead, they’ll become independent indices.
     * If you want to only remove the records from the index, use the clear method.
     * Deleting an index will have no impact on Analytics data because you cannot delete an index’s Analytics data.
     * When deleting an inexistent index, the engine ignores the operation but does not send back an error.
     *
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun deleteIndex(
        requestOptions: RequestOptions? = null
    ): DeletionIndex

    /**
     * Convenience method. Perform [copyIndex] with a specified [Scope] of [Scope.Rules].
     *
     * @see copyIndex
     *
     * @param destination [IndexName] of the destination [Index].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun copyRules(
        destination: IndexName,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Convenience method. Perform [copyIndex] with a specified [Scope] of [Scope.Settings].
     *
     * @see copyIndex
     *
     * @param destination [IndexName] of the destination [Index].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun copySettings(
        destination: IndexName,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Convenience method. Perform [copyIndex] with a specified [Scope] of [Scope.Synonyms].
     *
     * @see copyIndex
     *
     * @param destination [IndexName] of the destination [Index].
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun copySynonyms(
        destination: IndexName,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * @return true if the index exists on the remote server. An index exists if at least one object has been saved, or
     * if [Settings] have been saved.
     */
    public suspend fun exists(): Boolean
}
