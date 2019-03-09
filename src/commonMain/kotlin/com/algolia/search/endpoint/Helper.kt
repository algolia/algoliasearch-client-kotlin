package com.algolia.search.endpoint

import com.algolia.search.filter.*
import com.algolia.search.model.Attribute
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.search.Query

internal val groupAnd = GroupAnd("conjunctive")
internal val groupOr = GroupOr("disjunctive")

internal fun EndpointSearchImpl.buildAndQueries(
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

internal fun EndpointSearchImpl.buildOrQueries(
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