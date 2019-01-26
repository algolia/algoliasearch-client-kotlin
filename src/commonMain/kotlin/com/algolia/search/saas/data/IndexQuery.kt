package com.algolia.search.saas.data

import com.algolia.search.saas.data.search.Query


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)