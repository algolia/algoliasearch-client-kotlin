package com.algolia.search.saas.model.multiple_index

import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.model.search.Query


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)