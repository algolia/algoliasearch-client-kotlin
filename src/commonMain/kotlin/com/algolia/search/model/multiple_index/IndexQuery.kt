package com.algolia.search.model.multiple_index

import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)