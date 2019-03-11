package com.algolia.search.endpoint

import com.algolia.search.client.*
import com.algolia.search.filter.*
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json


internal class EndpointSearchImpl(
    val api: APIWrapper,
    override val indexName: IndexName
) : EndpointSearch,
    APIWrapper by api {

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.build()

        return retryRead(requestOptions, indexName.toPath("/query")) { url ->
            httpClient.post<ResponseSearch>(url) {
                setBody(copy)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): ResponseSearch {
        val copy = query?.build()
        val bodyString =
            copy?.let {
                json {
                    KeyParams to Json.noDefaults.toJson(Query.serializer(), it).jsonObject.urlEncode()
                }.toString()
            } ?: "{}"

        return retryRead(requestOptions, indexName.toPath("/browse")) { url ->
            httpClient.post<ResponseSearch>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun browse(cursor: Cursor, requestOptions: RequestOptions?): ResponseSearch {
        return retryRead(requestOptions, indexName.toPath("/browse")) { url ->
            httpClient.get<ResponseSearch>(url) {
                parameter(KeyCursor, cursor)
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun searchForFacetValue(
        attribute: Attribute,
        facetQuery: String?,
        query: Query?,
        maxFacetHits: Int?,
        requestOptions: RequestOptions?
    ): ResponseSearchForFacetValue {
        val copy = query?.build()
        val extraParams = json {
            maxFacetHits?.let { KeyMaxFacetHits to it }
            facetQuery?.let { KeyFacetQuery to it }
        }
        val bodyString = (copy?.toJsonNoDefaults()?.merge(extraParams) ?: extraParams).toString()

        return retryRead(
            requestOptions,
            indexName.toPath("/facets/$attribute/query")
        ) { url ->
            httpClient.post<ResponseSearchForFacetValue>(url) {
                body = bodyString
                setRequestOptions(requestOptions)
            }
        }
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
        val results = EndpointMultipleIndexImpl(api).multipleQueries(queryAnd.plus(queriesOr)).results
        val resultAnd = results.first()
        val resultsOr = results.subList(1, results.size)
        val facets = resultsOr.map { it.facets.toMutableMap() }.reduce { acc, map -> acc.apply { this += map } }

        return resultAnd.copy(
            disjunctiveFacetsOrNull = facets,
            exhaustiveFacetsCountOrNull = resultsOr.any { it.exhaustiveFacetsCountOrNull == true }
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
            filterBuilder {
                groupAnd += andFilters
                groupOr += orFilters
            }
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
                filterBuilder {
                    groupAnd += andFilters
                    groupOr += orFilters.filter { it.attribute != attribute }
                }
                hitsPerPage = 0
                analytics = false
            }
        }.map { IndexQuery(indexName, it) }
    }
}