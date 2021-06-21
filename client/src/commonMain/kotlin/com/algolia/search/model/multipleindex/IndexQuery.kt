package com.algolia.search.model.multipleindex

import com.algolia.search.endpoint.EndpointMultipleIndex
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import kotlinx.serialization.Serializable

/**
 * Associate a [Query] to a specific [IndexName]. Used by [EndpointMultipleIndex.multipleQueries].
 */
@Serializable
public data class IndexQuery(
    val indexName: IndexName,
    val query: Query = Query()
)
