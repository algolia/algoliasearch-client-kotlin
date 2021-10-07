package com.algolia.search.endpoint

import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.apikey.ACL
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.multipleindex.FacetIndexQuery
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.multipleindex.IndexedQuery
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy
import com.algolia.search.model.multipleindex.RequestObjects
import com.algolia.search.model.response.ResponseBatches
import com.algolia.search.model.response.ResponseListAPIKey
import com.algolia.search.model.response.ResponseListIndices
import com.algolia.search.model.response.ResponseMultiSearch
import com.algolia.search.model.response.ResponseObjects
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions

public interface EndpointMultipleIndex {

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-methods/list-indices/?language=kotlin]
     * Get a list of indices with their associated metadata.
     * This method retrieves a list of all indices associated with a given [ApplicationID].
     * The returned list includes the name of the index as well as its associated metadata,
     * such as the number of records, size, last build time, and pending tasks.
     * Calling this method returns all indices, with no paging. So if there are 1000s of indices for a certain
     * [ApplicationID], then all 1000 indices will be returned at the same time.
     * The returned list complies with any [ACL] restrictions of the [APIKey] used to retrieve them.
     * For example, if you’re using an [APIKey] that only has access to some indices, you will only retrieve these.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun listIndices(requestOptions: RequestOptions? = null): ResponseListIndices

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-methods/list-api-keys/?language=kotlin]
     * Get the full list of API Keys.
     *
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun listIndexAPIKeys(requestOptions: RequestOptions? = null): ResponseListAPIKey

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-methods/multiple-queries/?language=kotlin]
     *
     * Perform a search on several indices at the same time, with one method call.
     * The returned results are broken down by [IndexQuery].
     * This method can be used in several different kinds of situations. Here are two typical usage scenarios:
     *
     * 1. You have multiple indices that serve different purposes. This is typical when you want to search your main
     * index as well as an index that contains a history of searches (to be used for autocomplete).
     *
     * 2. You want to target one index and send it multiple queries, where, for example, each query contains different
     * settings or filters, or the query itself is slightly adjusted.
     *
     * Note that for 2., you will want to use the [MultipleQueriesStrategy.StopIfEnoughMatches] value of the [strategy]
     * parameter.
     *
     * @param queries The [IndexQuery] that will execute each [Query] against its [IndexName]
     * @param strategy The [MultipleQueriesStrategy] of the query.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun multipleQueries(
        queries: List<IndexQuery>,
        strategy: MultipleQueriesStrategy? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearches

    /**
     * [Documentation][https://www.algolia.com/doc/rest-api/search/#get-objects]
     *
     * Retrieve one or more objects, potentially from different indices, in a single API call.
     * Results will be received in the same order as the requests.
     *
     * @param requests The list of objects to retrieve.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun multipleGetObjects(
        requests: List<RequestObjects>,
        requestOptions: RequestOptions? = null
    ): ResponseObjects

    /**
     * Perform several indexing operations in one API call.
     * This method enables you to batch multiple different indexing operations in one API call, like add or delete
     * objects, potentially targeting multiple indices.
     *
     * You would use this method to:
     *
     * - reduce latency - only one network trip is required for multiple operations
     * - ensure data integrity - all operations inside the batch will be executed atomically. Meaning that instead of
     *   deleting 30 objects then adding 20 new objects in two operations, we do both operation in one go.
     *   This will remove the time during which an index is in an inconsistent state and could be a great
     *   alternative to doing an atomic reindexing using a temporary index.
     *
     * When batching of a large numbers of objects, or large sizes, be aware of our rate limit.
     * You’ll know you’ve reached the rate limit when you start receiving errors on your indexing operations.
     * This can only be resolved if you wait before sending any further indexing operations.

     * @param operations List of [BatchOperationIndex].
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun multipleBatchObjects(
        operations: List<BatchOperationIndex>,
        requestOptions: RequestOptions? = null
    ): ResponseBatches

    /**
     * Perform a search on several indices at the same time, with one method call.
     * The returned results are broken down by [IndexedQuery].
     * This method can be used in several kinds of situations. Here are three typical usage scenarios:
     *
     * 1. You have multiple indices that serve different purposes. This is typical when you want to search your main
     * index as well as an index that contains a history of searches (to be used for autocomplete).
     *
     * 2. You want to target one index and send it multiple queries, where, for example, each query contains different
     * settings or filters, or the query itself is slightly adjusted.
     *
     * 3. You want to perform multiple [IndexQuery] and [FacetIndexQuery] queries at the same time.
     *
     * Note that for 2., you will want to use the [MultipleQueriesStrategy.StopIfEnoughMatches] value of the [strategy]
     * parameter.
     *
     * @param requests The [IndexedQuery] that will execute each [Query] against its [IndexName]
     * @param strategy The [MultipleQueriesStrategy] of the query.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun search(
        requests: List<IndexedQuery>,
        strategy: MultipleQueriesStrategy? = null,
        requestOptions: RequestOptions? = null
    ): ResponseMultiSearch
}
