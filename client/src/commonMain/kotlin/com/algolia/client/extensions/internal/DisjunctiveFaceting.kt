package com.algolia.client.extensions.internal

import com.algolia.client.extensions.SearchDisjunctiveFacetingResponse
import com.algolia.client.model.search.Exhaustive
import com.algolia.client.model.search.SearchForHits
import com.algolia.client.model.search.SearchResponse

/**
 * Helper making multiple queries for disjunctive faceting
 * and merging the multiple search responses into a single one with
 * combined facets information
 */
internal data class DisjunctiveFaceting(
  val query: SearchForHits,
  val refinements: Map<String, List<String>>,
  val disjunctiveFacets: Set<String>,
) {
  // Build filters SQL string from the provided refinements and disjunctive facets set
  internal fun buildFilters(excludedAttribute: String?): String {
    val filters = refinements.entries.sortedBy { it.key }.filter {
      it.key != excludedAttribute && it.value.isNotEmpty()
    }.map {
      val facetOperator = if (disjunctiveFacets.contains(it.key)) " OR " else " AND "
      val expression = it.value.joinToString(facetOperator) { value -> """"${it.key}":"$value"""" }
      return@map "($expression)"
    }
    return filters.joinToString(" AND ")
  }

  /*
   * Build search queries to fetch the necessary facets information for disjunctive faceting
   * If the disjunctive facets set is empty, makes a single request with applied conjunctive filters
   */
  fun buildQueries(): List<SearchForHits> {
    val queries = mutableListOf<SearchForHits>()

    val mainQueryFilters = listOf(
      query.filters,
      buildFilters(null),
    ).mapNotNull { it }
      .filter { it.isNotEmpty() }
      .joinToString(" AND ")

    queries.add(query.copy(filters = mainQueryFilters))

    disjunctiveFacets.sortedWith(compareBy { it }).forEach { facet ->
      val disjunctiveQuery = query.copy(
        facets = listOf(facet),
        filters = listOf(
          query.filters,
          buildFilters(facet),
        ).mapNotNull { it }
          .filter { it.isNotEmpty() }
          .joinToString(" AND "),
        hitsPerPage = 0,
        attributesToRetrieve = emptyList(),
        attributesToHighlight = emptyList(),
        attributesToSnippet = emptyList(),
        analytics = false,
      )

      queries.add(disjunctiveQuery)
    }

    return queries
  }

  // Get applied disjunctive facet values for provided attribute
  internal fun appliedDisjunctiveFacetValues(attribute: String): Set<String> {
    if (!disjunctiveFacets.contains(attribute)) {
      return emptySet()
    }

    return refinements[attribute]?.toSet() ?: emptySet()
  }

  // Merge received search responses into single one with combined facets information
  fun mergeResponses(
    responses: List<SearchResponse>,
  ): SearchDisjunctiveFacetingResponse {
    val mainResponse = responses.first()
    val responsesForDisjunctiveFaceting = responses.drop(1)

    val mergedDisjunctiveFacets = mutableMapOf<String, Map<String, Int>>()
    val mergedFacetStats = mainResponse.facetsStats?.toMutableMap() ?: mutableMapOf()
    var mergedExhaustiveFacetsCount = mainResponse.exhaustive?.facetsCount ?: false

    for (result in responsesForDisjunctiveFaceting) {
      // Merge facet values
      for ((attribute, facets) in result.facets ?: emptyMap()) {
        // Complete facet values applied in the filters
        // but missed in the search response
        val missingFacets = appliedDisjunctiveFacetValues(attribute).subtract(facets.keys).associateWith { 0 }
        mergedDisjunctiveFacets[attribute] = facets + missingFacets
      }

      // Merge facets stats
      mergedFacetStats.putAll(result.facetsStats ?: emptyMap())

      // If facet counts are not exhaustive, propagate this information to the main results.
      // Because disjunctive queries are less restrictive than the main query, it can happen that the main query
      // returns exhaustive facet counts, while the disjunctive queries do not.
      if (result.exhaustive?.facetsCount != null) {
        mergedExhaustiveFacetsCount = mergedExhaustiveFacetsCount && (result.exhaustive.facetsCount)
      }
    }

    return SearchDisjunctiveFacetingResponse(
      response = mainResponse.copy(
        facetsStats = mergedFacetStats,
        exhaustive = mainResponse.exhaustive?.copy(
          facetsCount = mergedExhaustiveFacetsCount,
        ) ?: Exhaustive(facetsCount = mergedExhaustiveFacetsCount),
      ),
      disjunctiveFacets = mergedDisjunctiveFacets,
    )
  }
}
