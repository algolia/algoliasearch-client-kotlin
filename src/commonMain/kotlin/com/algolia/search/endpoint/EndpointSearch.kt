package com.algolia.search.endpoint

import com.algolia.search.transport.RequestOptions
import com.algolia.search.filter.FilterFacet
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query


public interface EndpointSearch {

    val indexName: IndexName

    suspend fun search(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun browse(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun browse(cursor: Cursor, requestOptions: RequestOptions? = null): ResponseSearch

    suspend fun searchForFacetValue(
        attribute: Attribute,
        facetQuery: String? = null,
        query: Query? = null,
        maxFacetHits: Int? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchForFacetValue

    suspend fun searchDisjunctiveFacets(
        query: Query,
        disjunctiveFacets: List<Attribute>,
        filters: List<FilterFacet>,
        requestOptions: RequestOptions? = null
    ): ResponseSearch
}