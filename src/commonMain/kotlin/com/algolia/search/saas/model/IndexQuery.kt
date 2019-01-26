package com.algolia.search.saas.model

import com.algolia.search.saas.model.search.Query


data class IndexQuery(
    val indexName: IndexName,
    val query: Query
)