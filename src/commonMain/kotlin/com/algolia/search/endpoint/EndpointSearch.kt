package com.algolia.search.endpoint

import com.algolia.search.filter.FilterFacet
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions


public interface EndpointSearch {

    val indexName: IndexName

    /**
     * Method used for querying an index.
     * The search query only allows for the retrieval of up to 1000 hits.
     * If you need to retrieve more than 1000 hits (e.g. for SEO), you can either leverage
     * the [Browse index][https://www.algolia.com/doc/api-reference/api-methods/browse/] method or increase
     * the [paginationLimitedTo][https://www.algolia.com/doc/api-reference/api-parameters/paginationLimitedTo/] parameter.
     *
     * @param query The [Query] used to search.
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearch].
     */
    suspend fun search(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun browse(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun browse(cursor: Cursor, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun searchForFacetValues(
        attribute: Attribute,
        facetQuery: String? = null,
        query: Query? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchForFacetValue

    suspend fun searchDisjunctiveFacets(
        query: Query,
        disjunctiveFacets: List<Attribute>,
        filters: List<FilterFacet>,
        requestOptions: RequestOptions? = null
    ): ResponseSearch
}