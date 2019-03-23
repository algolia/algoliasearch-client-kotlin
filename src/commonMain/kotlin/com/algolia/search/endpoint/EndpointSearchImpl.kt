package com.algolia.search.endpoint

import com.algolia.search.configuration.CallType
import com.algolia.search.filter.*
import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.FacetStats
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

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val body = query.toBody()

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/query"), requestOptions, body)
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val body = query?.let {
            json {
                KeyParams to Json.noDefaults.toJson(Query.serializer(), it).jsonObject.urlEncode()
            }.toString()
        } ?: "{}"

        return transport.request(HttpMethod.Post, CallType.Read, indexName.toPath("/browse"), requestOptions, body)
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        val options = requestOptionsBuilder(requestOptions) {
            parameter(KeyCursor, cursor)
        }

        return transport.request(HttpMethod.Get, CallType.Read, indexName.toPath("/browse"), options)
    }

    override suspend fun searchForFacetValues(
        attribute: Attribute,
        facetQuery: String?,
        query: Query?,
        requestOptions: RequestOptions?
    ): ResponseSearchForFacetValue {
        val path = indexName.toPath("/facets/$attribute/query")
        val extraParams = json {
            facetQuery?.let { KeyFacetQuery to it }
        }
        val body = (query?.toJsonNoDefaults()?.merge(extraParams) ?: extraParams).toString()

        return transport.request(HttpMethod.Post, CallType.Read, path, requestOptions, body)
    }

    override suspend fun searchDisjunctiveFacets(
        query: Query,
        disjunctiveFacets: List<Attribute>,
        filters: List<FilterFacet>,
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

        resultAnd.facetStatsOrNull?.let {
            facetStats += it
        }
        resultsOr.forEach { result ->
            result.facetStatsOrNull?.let {
                facetStats += it
            }
        }
        return resultAnd.copy(
            disjunctiveFacetsOrNull = facets,
            exhaustiveFacetsCountOrNull = resultsOr.any { it.exhaustiveFacetsCountOrNull == true },
            facetStatsOrNull = if (facetStats.isEmpty()) null else facetStats
        )
    }

    private val groupAnd = GroupAnd("conjunctive")
    private val groupOr = GroupOr("disjunctive")

    private fun buildAndQueries(
        query: Query,
        andFilters: List<FilterFacet>,
        orFilters: List<FilterFacet>
    ): List<IndexQuery> {
        return query.copy().apply {
            filters = FilterBuilder {
                groupAnd += andFilters
                groupOr += orFilters
            }.build()
        }.let { listOf(IndexQuery(indexName, it)) }
    }

    private fun buildOrQueries(
        disjunctiveFacets: List<Attribute>,
        query: Query,
        andFilters: List<FilterFacet>,
        orFilters: List<FilterFacet>
    ): List<IndexQuery> {
        return disjunctiveFacets.map { attribute ->
            query.copy().apply {
                setFacets(attribute)
                setAttributesToRetrieve()
                setAttributesToHighlight()
                filters = FilterBuilder {
                    groupAnd += andFilters
                    groupOr += orFilters.filter { it.attribute != attribute }
                }.build()
                hitsPerPage = 0
                analytics = false
            }
        }.map { IndexQuery(indexName, it) }
    }
}