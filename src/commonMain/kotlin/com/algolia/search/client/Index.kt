package com.algolia.search.client

import com.algolia.search.dsl.filters
import com.algolia.search.endpoint.*
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchRules
import com.algolia.search.model.response.ResponseSearchSynonyms
import com.algolia.search.model.response.ResponseSearches
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.RuleQuery
import com.algolia.search.model.search.Facet
import com.algolia.search.model.search.FacetStats
import com.algolia.search.model.search.Query
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymQuery
import com.algolia.search.transport.RequestOptions
import com.algolia.search.transport.Transport


/**
 * The main entry point for performing operations on a single index.
 */
public data class Index internal constructor(
    internal val transport: Transport,
    override val indexName: IndexName
) : EndpointSearch by EndpointSearchImpl(transport, indexName),
    EndpointSettings by EndpointSettingsImpl(transport, indexName),
    EndpointAdvanced by EndpointAdvancedImpl(transport, indexName),
    EndpointIndex by EndpointIndexImpl(transport, indexName),
    EndpointIndexing by EndpointIndexingImpl(transport, indexName),
    EndpointSynonym by EndpointSynonymImpl(transport, indexName),
    EndpointRule by EndpointRuleImpl(transport, indexName) {

    /**
     * Iterate over all [Rule] in the index.
     *
     * @see [searchRules]
     *
     * @param query The [RuleQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseRules(
        query: RuleQuery = RuleQuery(),
        requestOptions: RequestOptions? = null
    ): List<ResponseSearchRules> {
        val responses = mutableListOf<ResponseSearchRules>()
        var page = 0

        while (true) {
            val response = searchRules(query.copy(page = page++), requestOptions)

            if (response.hits.isEmpty()) break
            responses += response
        }
        return responses
    }

    /**
     * Iterate over all [Synonym] in the index.
     *
     * @see [searchSynonyms]
     *
     * @param query The [SynonymQuery] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseSynonyms(
        query: SynonymQuery = SynonymQuery(),
        requestOptions: RequestOptions? = null
    ): List<ResponseSearchSynonyms> {
        val responses = mutableListOf<ResponseSearchSynonyms>()
        var page = 0

        while (true) {
            val response = searchSynonyms(query.copy(page = page++), requestOptions)

            if (response.hits.isEmpty()) break
            responses += response
        }
        return responses
    }

    /**
     * Iterate over all objects in the index.
     *
     * @see [browse]
     *
     * @param query The [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browseObjects(
        query: Query = Query(),
        requestOptions: RequestOptions? = null
    ): List<ResponseSearch> {
        val responses = mutableListOf<ResponseSearch>()
        val initial = browse(query, requestOptions)
        var cursor = initial.cursorOrNull

        responses += initial
        while (cursor != null) {
            val response = browse(cursor)

            responses += response
            cursor = response.cursorOrNull
        }
        return responses
    }

    suspend fun searchHierarchical(
        query: Query = Query(),
        disjunctiveFacets: List<Attribute> = listOf(),
        filters: Set<Filter> = setOf(),
        hierarchicalAttributes: List<Attribute> = listOf(),
        hierarchicalFilters: List<Filter.Facet> = listOf()
    ): ResponseSearch {
        val (filtersOr, filtersAnd) = filters.partition { disjunctiveFacets.contains(it.attribute) }
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
        val queriesForHierarchicalFacets = hierarchicalAttributes
            .take(hierarchicalFilters.size + 1)
            .mapIndexed { index, attribute ->
                query
                    .toIndexQuery()
                    .filters(filtersAnd.combine(hierarchicalFilters.getOrNull(index - 1)).minus(hierarchicalFilters.last()), filtersOrFacet, filtersOrTag, filtersOrNumeric)
                    .setFacets(attribute)
                    .optimize()
            }
        val queries = listOf(queryForResults) + queriesForDisjunctiveFacets + queriesForHierarchicalFacets
        val response = EndpointMultipleIndexImpl(transport).multipleQueries(queries)

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