@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.configuration.CallType
import com.algolia.search.dsl.filters
import com.algolia.search.endpoint.EndpointSearch
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.filter.FilterGroupsConverter
import com.algolia.search.model.internal.request.RequestCursor
import com.algolia.search.model.internal.request.RequestParams
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.response.ResponseHitWithPosition
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacets
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Facet
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.merge
import com.algolia.search.serialize.internal.toBody
import com.algolia.search.serialize.internal.toJsonNoDefaults
import com.algolia.search.serialize.internal.urlEncode
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.internal.Transport
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal class EndpointSearchImpl(
    private val transport: Transport,
    override val indexName: IndexName,
) : EndpointSearch {

    override suspend fun search(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val body = query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/query"), requestOptions, body)
    }

    override tailrec suspend fun findObject(
        match: (ResponseSearch.Hit) -> Boolean,
        query: Query,
        paginate: Boolean,
        requestOptions: RequestOptions?,
    ): ResponseHitWithPosition? {
        val response = search(query, requestOptions)
        val hit = response.hits.find(match)
        val hasNextPage = response.page + 1 < response.nbPages

        return if (hit != null) {
            ResponseHitWithPosition(hit, response.hits.indexOf(hit), response.page)
        } else if (paginate && hasNextPage) {
            findObject(match, query.copy(page = (query.page ?: 0) + 1), paginate, requestOptions)
        } else null
    }

    override suspend fun browse(query: Query, requestOptions: RequestOptions?): ResponseSearch {
        val params = RequestParams(query.toJsonNoDefaults().urlEncode())
        val body = JsonNoDefaults.encodeToString(RequestParams.serializer(), params)

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/browse"), requestOptions, body)
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        val params = RequestCursor(cursor.toString())
        val body = JsonNoDefaults.encodeToString(RequestCursor.serializer(), params)

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/browse"), requestOptions, body)
    }

    override suspend fun searchForFacets(
        attribute: Attribute,
        facetQuery: String?,
        query: Query,
        requestOptions: RequestOptions?,
    ): ResponseSearchForFacets {
        val path = indexName.toPath("/facets/$attribute/query")
        val extraParams = buildJsonObject {
            facetQuery?.let { put(Key.FacetQuery, it) }
        }
        val body = query.toJsonNoDefaults().merge(extraParams).toString()

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun advancedSearch(
        query: Query,
        filterGroups: Set<FilterGroup<*>>,
        requestOptions: RequestOptions?,
    ): ResponseSearch {
        val filtersAnd = filterGroups.filterIsInstance<FilterGroup.And<*>>().flatten()
        val filtersOr = filterGroups.filterIsInstance<FilterGroup.Or<*>>().flatten()
        val disjunctiveFacets = filtersOr.map { it.attribute }.toSet()
        val filtersOrFacet = filtersOr.filterIsInstance<Filter.Facet>()
        val filtersOrTag = filtersOr.filterIsInstance<Filter.Tag>()
        val filtersOrNumeric = filtersOr.filterIsInstance<Filter.Numeric>()
        val queryForResults = query.toIndexQuery().setFilters(filterGroups)
        val queriesForDisjunctiveFacets = disjunctiveFacets.map { attribute ->
            val groups = filterGroups.map { group ->
                if (group is FilterGroup.Or.Facet) {
                    FilterGroup.Or.Facet(group.filter { filter -> filter.attribute != attribute }.toSet())
                } else group
            }

            query
                .toIndexQuery()
                .setFacets(attribute)
                .optimize()
                .setFilters(groups.toSet())
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
        val response = EndpointMultipleIndex(transport).multipleQueries(queries, requestOptions = requestOptions)

        return response.aggregateResult(disjunctiveFacets.size)
    }

    private fun IndexQuery.setFilters(groups: Set<FilterGroup<*>>): IndexQuery {
        query.filters = FilterGroupsConverter.SQL(groups)
        return this
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
        filtersOrNumeric: List<Filter.Numeric>,
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

/**
 * Create an [EndpointSearch] instance.
 */
internal fun EndpointSearch(
    transport: Transport,
    indexName: IndexName,
): EndpointSearch = EndpointSearchImpl(transport, indexName)
