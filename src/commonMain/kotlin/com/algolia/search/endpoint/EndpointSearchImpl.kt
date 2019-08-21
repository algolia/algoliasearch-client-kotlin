package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.filters
import com.algolia.search.dsl.requestOptionsBuilder
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.request.RequestParams
import com.algolia.search.model.response.ResponseHitsWithPosition
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacets
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Facet
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.json


internal class EndpointSearchImpl(
    private val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch {

    override suspend fun search(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val body = query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/query"), requestOptions, body)
    }

    override tailrec suspend fun findFirstObject(
        match: (ResponseSearch.Hit) -> Boolean,
        query: Query,
        doNotPaginate: Boolean,
        requestOptions: RequestOptions?
    ): ResponseHitsWithPosition? {
        val response = search(query, requestOptions)
        val hit = response.hits.find(match)
        val hasNextPage = response.page + 1 < response.nbPages

        return if (hit != null) {
            ResponseHitsWithPosition(hit, response.hits.indexOf(hit), response.page)
        } else if (!doNotPaginate && hasNextPage) {
            findFirstObject(match, query.copy(page = (query.page ?: 0) + 1), doNotPaginate, requestOptions)
        } else null
    }

    override suspend fun browse(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val params = RequestParams(query.toJsonNoDefaults().urlEncode())
        val body = JsonNoDefaults.stringify(RequestParams.serializer(), params)

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
        facetQuery: String?,
        query: Query,
        requestOptions: RequestOptions?
    ): ResponseSearchForFacets {
        val path = indexName.toPath("/facets/$attribute/query")
        val extraParams = json {
            facetQuery?.let { KeyFacetQuery to it }
        }
        val body = query.toJsonNoDefaults().merge(extraParams).toString()

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun advancedSearch(
        query: Query,
        filterGroups: Set<FilterGroup<*>>,
        requestOptions: RequestOptions?
    ): ResponseSearch {
        val filtersAnd = filterGroups.filterIsInstance<FilterGroup.And<*>>().flatten()
        val filtersOr = filterGroups.filterIsInstance<FilterGroup.Or<*>>().flatten()
        val disjunctiveFacets = filtersOr.map { it.attribute }.toSet()
        val filtersOrFacet = filtersOr.filterIsInstance<Filter.Facet>()
        val filtersOrTag = filtersOr.filterIsInstance<Filter.Tag>()
        val filtersOrNumeric = filtersOr.filterIsInstance<Filter.Numeric>()
        val queryForResults = query
            .toIndexQuery()
            .filters(filtersAnd, filtersOrFacet, filtersOrTag, filtersOrNumeric)
        val queriesForDisjunctiveFacets = disjunctiveFacets.map { attribute ->
            query
                .toIndexQuery()
                .filters(
                    filtersAnd,
                    filtersOrFacet.filter { it.attribute != attribute },
                    filtersOrTag,
                    filtersOrNumeric
                )
                .setFacets(attribute)
                .optimize()
        }
        val queriesForHierarchicalFacets = filterGroups.filterIsInstance<FilterGroup.And.Hierarchical>().flatMap {
            it.attributes
                .take(it.path.size + 1)
                .mapIndexed { index, attribute ->
                    query
                        .toIndexQuery()
                        .filters(
                            filtersAnd.combine(it.path.getOrNull(index - 1)).minus(it.path.last()),
                            filtersOrFacet,
                            filtersOrTag,
                            filtersOrNumeric
                        )
                        .setFacets(attribute)
                        .optimize()
                }
        }
        val queries = listOf(queryForResults) + queriesForDisjunctiveFacets + queriesForHierarchicalFacets
        val response = EndpointMultipleIndexImpl(transport).multipleQueries(queries, requestOptions = requestOptions)

        return response.aggregateResult(disjunctiveFacets.size)
    }

    private fun List<ResponseSearch>.aggregateFacets(): Map<Attribute, List<Facet>> {
        return fold(mapOf()) { acc, result ->
            result.facetsOrNull?.let { acc + it } ?: acc
        }
    }

    private fun List<ResponseSearch>.aggregateFacetStats(): Map<Attribute, FacetStats> {
        return fold(mapOf()) { acc, result ->
            result.facetStatsOrNull?.let { acc + it } ?: acc
        }
    }

    private fun List<Filter>.combine(hierarchicalFilter: Filter.Facet?): List<Filter> {
        return hierarchicalFilter?.let { this + it } ?: this
    }

    private fun ResponseSearches.aggregateResult(disjunctiveFacetCount: Int): ResponseSearch {
        val resultsDisjunctiveFacets = results.subList(1, 1 + disjunctiveFacetCount)
        val resultHierarchicalFacets = results.subList(1 + disjunctiveFacetCount, results.size)
        val facets = resultsDisjunctiveFacets.aggregateFacets()
        val facetStats = results.aggregateFacetStats()
        val hierarchicalFacets = resultHierarchicalFacets.aggregateFacets()

        return results.first().copy(
            facetStatsOrNull = if (facetStats.isEmpty()) null else facetStats,
            disjunctiveFacetsOrNull = facets,
            hierarchicalFacetsOrNull = if (hierarchicalFacets.isEmpty()) null else hierarchicalFacets,
            exhaustiveFacetsCountOrNull = resultsDisjunctiveFacets.all { it.exhaustiveFacetsCountOrNull == true }
        )
    }

    private fun IndexQuery.optimize(): IndexQuery {
        query.apply {
            attributesToRetrieve = listOf()
            attributesToHighlight = listOf()
            hitsPerPage = 0
            analytics = false
        }
        return this
    }

    private fun Query.toIndexQuery(): IndexQuery {
        return IndexQuery(indexName, copy())
    }

    private fun IndexQuery.filters(
        filtersAnd: List<Filter>,
        filtersOrFacet: List<Filter.Facet>,
        filtersOrTag: List<Filter.Tag>,
        filtersOrNumeric: List<Filter.Numeric>
    ): IndexQuery {
        query.apply {
            filters {
                and { +filtersAnd }
                orFacet { +filtersOrFacet }
                orTag { +filtersOrTag }
                orNumeric { +filtersOrNumeric }
            }
        }
        return this
    }

    private fun IndexQuery.setFacets(facet: Attribute?): IndexQuery {
        if (facet != null) query.facets = setOf(facet)
        return this
    }
}