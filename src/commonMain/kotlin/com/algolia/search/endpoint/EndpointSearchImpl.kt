package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.dsl.filters
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.request.RequestParams
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValues
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.FacetValuesQuery
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


internal class EndpointSearchImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch {

    override suspend fun search(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val body = query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/query"), requestOptions, body)
    }

    override suspend fun browse(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val params = RequestParams(query.toJsonNoDefaults().urlEncode())
        val body = Json.noDefaults.stringify(RequestParams.serializer(), params)

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/browse"), requestOptions, body)
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyCursor, cursor)
        }

        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/browse"), options)
    }

    override suspend fun searchForFacets(
        attribute: Attribute,
        query: FacetValuesQuery,
        requestOptions: RequestOptions?
    ): ResponseSearchForFacetValues {
        val path = indexName.toPath("/facets/$attribute/query")
        val extraParams = json {
            query.facetQuery?.let { KeyFacetQuery to it }
        }
        val body = (query.query?.toJsonNoDefaults()?.merge(extraParams) ?: extraParams).toString()

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun searchDisjunctiveFacets(
        query: Query,
        disjunctiveFacets: List<Attribute>,
        filters: List<Filter.Facet>,
        requestOptions: RequestOptions?
    ): ResponseSearch {
        val (orFilters, andFilters) = filters.partition { disjunctiveFacets.contains(it.attribute) }
        val queryAnd = buildAndQueries(query, andFilters, orFilters)
        val queriesOr = buildOrQueries(disjunctiveFacets, query, andFilters, orFilters)
        val results = EndpointMultipleIndexImpl(transport).multipleQueries(queryAnd.plus(queriesOr)).results
        val resultAnd = results.first()
        val resultsOr = results.subList(1, results.size)
        val facets = resultsOr.map { it.facets.toMutableMap() }.reduce { acc, map -> acc.apply { this += map } }
        val facetStats = mutableMapOf<Attribute, FacetStats>()

        resultAnd.facetStatsOrNull?.let { facetStats += it }
        resultsOr.forEach { result ->
            result.facetStatsOrNull?.let { facetStats += it }
        }
        return resultAnd.copy(
            disjunctiveFacetsOrNull = facets,
            exhaustiveFacetsCountOrNull = resultsOr.any { it.exhaustiveFacetsCountOrNull == true },
            facetStatsOrNull = if (facetStats.isEmpty()) null else facetStats
        )
    }

    private fun buildAndQueries(
        query: Query,
        andFilters: List<Filter.Facet>,
        orFilters: List<Filter.Facet>
    ): List<IndexQuery> {
        return query.copy().apply {
            filters {
                and { +andFilters }
                orFacet { +orFilters }
            }
        }.let { listOf(IndexQuery(indexName, it)) }
    }

    private fun buildOrQueries(
        disjunctiveFacets: List<Attribute>,
        query: Query,
        andFilters: List<Filter.Facet>,
        orFilters: List<Filter.Facet>
    ): List<IndexQuery> {
        return disjunctiveFacets.map { attribute ->
            query.copy().apply {
                facets = listOf(attribute)
                attributesToRetrieve = listOf()
                attributesToHighlight = listOf()
                hitsPerPage = 0
                analytics = false
                filters {
                    and { +andFilters }
                    orFacet { +orFilters.filter { it.attribute != attribute } }
                }
            }
        }.map { IndexQuery(indexName, it) }
    }
}