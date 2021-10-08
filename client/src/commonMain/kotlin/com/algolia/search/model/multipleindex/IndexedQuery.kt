package com.algolia.search.model.multipleindex

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import kotlinx.serialization.Serializable

/**
 * Associate a typed [Query] to a specific [IndexName].
 */
public sealed interface IndexedQuery {
    public val indexName: IndexName
    public val query: Query
}

/**
 * Associate a [Query] to a specific [IndexName].
 */
@Serializable
public data class IndexQuery(
    override val indexName: IndexName,
    override val query: Query = Query()
) : IndexedQuery

/**
 * Associate a facets [Query] to a specific [IndexName].
 */
public class FacetIndexQuery(
    override val indexName: IndexName,
    override val query: Query,
    public val facetAttribute: Attribute,
    public val facetQuery: String? = null
) : IndexedQuery
