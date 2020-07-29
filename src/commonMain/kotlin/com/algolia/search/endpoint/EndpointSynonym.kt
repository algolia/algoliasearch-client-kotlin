package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.deletion.DeletionIndex
import com.algolia.search.model.response.revision.RevisionIndex
import com.algolia.search.model.response.revision.RevisionSynonym
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/synonyms/?language=kotlin]
 */
public interface EndpointSynonym {

    public val indexName: IndexName

    /**
     * Create or update a single [Synonym] on an index.
     * Whether you create or update a [Synonym], you must specify a unique [ObjectID]. If the [ObjectID] is not found in
     * the index, the method will automatically create a new [Synonym].
     *
     * @param synonym [Synonym] to save.
     * @param forwardToReplicas By default, this method applies only to the specified index. By making this true,
     * the method will also send the synonym to all replicas. Thus, if you want to forward your synonyms to replicas
     * you will need to specify that.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionSynonym

    /**
     *
     * Create or update multiple [Synonym].
     * This method enables you to create or update one or more [Synonym] in a single call.
     * You can also recreate your entire set of [Synonym] by using the [clearExistingSynonyms] parameter.
     * Note that each [Synonym] object counts as a single indexing operation.

     * @param synonyms List of [Synonym] to save.
     * @param forwardToReplicas By default, this method applies only to the specified index. By making this true,
     * the method will also send the synonym to all replicas. Thus, if you want to forward your synonyms to replicas
     * you will need to specify that.
     * @param clearExistingSynonyms Forces the engine to replace all synonyms, using an atomic save.
     * Normally, to replace all synonyms on an index, you would first clear the synonyms, using clearAllSynonyms,
     * and then create a new list. However, between the clear and the add, your index will have no synonyms.
     * This is where clearExistingSynonyms comes into play.
     * By adding this parameter, you do not need to use [clearSynonyms], it’s done for you.
     * This parameter tells the engine to delete all existing synonyms before recreating a new list from the synonyms
     * listed in the current call. This is the only way to avoid having no synonyms, ensuring that your index will
     * always provide a full list of synonyms to your end-users.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun saveSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean? = null,
        clearExistingSynonyms: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Get a single [Synonym] using its [ObjectID].
     *
     * @param objectID [ObjectID] of the [Synonym] to retrieve.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun getSynonym(
        objectID: ObjectID,
        requestOptions: RequestOptions? = null
    ): Synonym

    /**
     * Remove a single [Synonym] from an [indexName] using its [ObjectID].
     *
     * @param objectID [ObjectID] of the [Synonym] to delete.
     * @param forwardToReplicas Will delete synonym on all replicas. Without this parameter, or by setting it to false,
     * the method still applies the change only to the specified index. If you want to forward the delete to your
     * replicas you will need to set this parameter to true.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): DeletionIndex

    /**
     * Get all [Synonym] that match a [SynonymQuery].
     *
     * @param query The [SynonymQuery].
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun searchSynonyms(
        query: SynonymQuery = SynonymQuery(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchSynonyms

    /**
     * Remove all synonyms from an index. This is a convenience method to delete all synonyms at once.
     * This Clear All method should not be used on a production index to push a new list of synonyms because it will
     * result in a short down period during which the index would have no synonyms at all.
     * Instead, use the [saveSynonyms] method (with replaceExistingSynonyms set to true) to atomically replace all
     * synonyms of an index with no down time.
     *
     * @param forwardToReplicas Also replace synonyms on replicas.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun clearSynonyms(
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex

    /**
     * Push a new set of [Synonym] and erase all previous ones.
     * This method, like [EndpointIndexing.replaceAllObjects], guarantees zero downtime.
     * All existing [Synonym] are deleted and replaced with the new ones, in a single, atomic operation.
     *
     * @param synonyms A list of [Synonym].
     * @param forwardToReplicas Also replace synonyms on replicas.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun replaceAllSynonyms(
        synonyms: List<Synonym>,
        forwardToReplicas: Boolean? = null,
        requestOptions: RequestOptions? = null
    ): RevisionIndex
}
